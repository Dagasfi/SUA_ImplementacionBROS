package simulacion.roadstatus;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import sua.autonomouscar.context.interfaces.ICongestionContext;
import sua.autonomouscar.context.interfaces.IDistanceSensorContext;
import sua.autonomouscar.devices.interfaces.IDistanceSensor;
import sua.autonomouscar.infrastructure.devices.DistanceSensor;
import sua.autonomouscar.interfaces.ERoadStatus;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		ICongestionContext contextoCongestion = null;
		ServiceReference ref = (ServiceReference)this.context.getServiceReference(ICongestionContext.class);
		if(ref != null) {
			System.out.println("[SIM] - ROAD STATUS TO JAM!!!");
			contextoCongestion = (ICongestionContext) this.context.getService(ref);
			contextoCongestion.setCongestion(ERoadStatus.JAM);		
		}
		
		IDistanceSensorContext contextoSensorDistancia= null;
		ServiceReference ref2 = (ServiceReference)this.context.getServiceReference(IDistanceSensorContext.class);
		
		System.out.println("\n"+ this.context.getAllServiceReferences(IDistanceSensorContext.class.getName(), null) +"\n");
		if(ref2 != null) {
			System.out.println("[SIM] - DISTANCE SENSOR TO False!!!");
			contextoSensorDistancia = (IDistanceSensorContext) this.context.getService(ref2);
			contextoSensorDistancia.setDistanceSensorWorkingMode(false);;	
			System.out.println("[SIM] - DISTANCE SENSOR TO False!!!");

		}
		
		ServiceReference ref3 = (ServiceReference)this.context.getServiceReference(IDistanceSensor.class);
		
		if(ref3 != null) {
			System.out.println("[SIM] - DISTANCE SENSOR TO False!!!");
			DistanceSensor sensorDistancia = (DistanceSensor) this.context.getService(ref3);
			sensorDistancia.setWorking(false);
			System.out.println("[SIM] - "+ sensorDistancia.getId() +" SENSOR TO False!!!");

		}
		
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		ICongestionContext contextoCongestion = null;
		ServiceReference ref = (ServiceReference)this.context.getServiceReference(ICongestionContext.class);
		if(ref != null) {
			System.out.println("[SIM] - ROAD STATUS TO FLUID!!!");
			contextoCongestion = (ICongestionContext) this.context.getService(ref);
			contextoCongestion.setCongestion(ERoadStatus.FLUID);		
		}
		Activator.context = null;
	}

}
