package com.mlorenzo.metrics;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.exporter.PushGateway;

@SpringBootApplication
public class SpringMetricsPushgatewayApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SpringMetricsPushgatewayApplication.class, args);
		
		// Cremos el registro de métricas.
		CollectorRegistry registry = new CollectorRegistry();
		
		// Creamos una métrica de tipo Contador llamada "students_total" y la resitramos en el registro creado anteriormente.
		Counter studentsCounter = Counter.build()
				.name("students_total").help("Total students.").register(registry);
		
		// Aquí podría haber una lógica más compleja para registrar estudiantes pero, en este caso, simplemente incrementamos el
		// valor del contador en 15689.
		studentsCounter.inc(15689);
		
		// Indicamos el host o ip y el puerto donde se encuenta ejecutándose el PushGateway.
		PushGateway pg = new PushGateway("127.0.0.1:9091");
		
		// Publicamos la métrica "studentsCounter" en el grupo "students-job" (si no existe, se crea) del PushGateway.
		pg.pushAdd(studentsCounter, "students-job");
	}

}
