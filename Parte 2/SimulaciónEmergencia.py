import simpy
import random
import matplotlib.pyplot as plt
import pandas as pd

RANDOM_SEED = 10
random.seed(RANDOM_SEED)

TIEMPO_SIMULACION = 1000
INTERVALO_LLEGADAS = 10 

capacidad_enfermeras = 2
capacidad_doctores = 2
capacidad_rayos_x = 1

tiempos_totales = []
tiempos_espera_triage = []
tiempos_espera_doctor = []
tiempos_espera_rayosx = []

class Paciente:
    def __init__(self, nombre, tiempo_llegada):
        self.nombre = nombre
        self.tiempo_llegada = tiempo_llegada
        self.severidad = None

    def asignar_severidad(self):
        self.severidad = random.randint(1, 5)

    def __str__(self):
        return f"{self.nombre} (Severidad {self.severidad})"

def inicializar_recursos(env):
    enfermeras = simpy.Resource(env, capacity=capacidad_enfermeras)
    doctores = simpy.PriorityResource(env, capacity=capacidad_doctores)
    rayos_x = simpy.PriorityResource(env, capacity=capacidad_rayos_x)
    return enfermeras, doctores, rayos_x

def realizar_triage(env, paciente, enfermeras):
    llegada_a_triage = env.now
    with enfermeras.request() as req:
        yield req
        yield env.timeout(10)
        paciente.asignar_severidad()

        print(f"[{env.now:.1f}] Triage completado para {paciente.nombre}. Severidad asignada: {paciente.severidad}")

    tiempo_espera = env.now - llegada_a_triage
    tiempos_espera_triage.append(tiempo_espera)

def proceso_paciente(env, nombre, enfermeras, doctores, rayos_x):
    paciente = Paciente(nombre, tiempo_llegada=env.now)

    yield env.process(realizar_triage(env, paciente, enfermeras))
    
    llegada_a_doctor = env.now
    with doctores.request(priority=paciente.severidad) as req:
        yield req
        yield env.timeout(15)

    tiempo_doctor = env.now - llegada_a_doctor
    tiempos_espera_doctor.append(tiempo_doctor)
    
    if random.random() < 0.5:
        llegada_a_rayosx = env.now
        with rayos_x.request(priority=paciente.severidad) as req:
            yield req
            yield env.timeout(20)

        tiempo_rx = env.now - llegada_a_rayosx
        tiempos_espera_rayosx.append(tiempo_rx)

    tiempo_total = env.now - paciente.tiempo_llegada
    tiempos_totales.append(tiempo_total)

    print(f"[{env.now:.1f}] {paciente.nombre} atendido completamente. Tiempo total en sistema: {tiempo_total:.1f}")

def generador_pacientes(env, enfermeras, doctores, rayos_x):
    i = 0
    while True:
        yield env.timeout(random.expovariate(1.0 / INTERVALO_LLEGADAS))
        i += 1
        nombre_paciente = f"Paciente {i}"
        env.process(proceso_paciente(env, nombre_paciente, enfermeras, doctores, rayos_x))

def mostrar_resultados():
    print("\n--- RESULTADOS DE LA SIMULACIÓN ---")
    print(f"Pacientes atendidos: {len(tiempos_totales)}")
    print(f"Tiempo promedio total por paciente: {sum(tiempos_totales)/len(tiempos_totales):.2f} unidades de tiempo")

    if tiempos_espera_triage:
        print(f"Promedio de espera en triage: {sum(tiempos_espera_triage)/len(tiempos_espera_triage):.2f}")
    if tiempos_espera_doctor:
        print(f"Promedio de espera con doctor: {sum(tiempos_espera_doctor)/len(tiempos_espera_doctor):.2f}")
    if tiempos_espera_rayosx:
        print(f"Promedio de espera en rayos X: {sum(tiempos_espera_rayosx)/len(tiempos_espera_rayosx):.2f}")

    plt.hist(tiempos_totales, bins=20, edgecolor='black')
    plt.title("Distribución de tiempos totales por paciente")
    plt.xlabel("Tiempo total en sistema")
    plt.ylabel("Número de pacientes")
    plt.grid(True)
    plt.tight_layout()
    plt.show()

def main():
    print("Iniciando simulación...")
    env = simpy.Environment()
    enfermeras, doctores, rayos_x = inicializar_recursos(env)
    env.process(generador_pacientes(env, enfermeras, doctores, rayos_x))
    env.run(until=TIEMPO_SIMULACION)
    mostrar_resultados()

if __name__ == "__main__":
    main()