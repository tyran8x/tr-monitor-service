package vn.tr.monitor.notifier;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.notify.AbstractEventNotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static de.codecentric.boot.admin.server.domain.values.StatusInfo.*;

@Slf4j
@Component
public class CustomNotifier extends AbstractEventNotifier {
	
	public CustomNotifier(InstanceRepository repository) {
		super(repository);
	}
	
	@Override
	@SuppressWarnings("all")
	protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
		return Mono.fromRunnable(() -> {
			if (event instanceof InstanceStatusChangedEvent) {
				String registName = instance.getRegistration().getName();
				String instanceId = event.getInstance().getValue();
				String status = ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus();
				String serviceUrl = instance.getRegistration().getServiceUrl();
				String statusName = switch (status) {
					case STATUS_UP -> "STATUS UP";
					case STATUS_OFFLINE -> "STATUS OFFLINE";
					case STATUS_RESTRICTED -> "STATUS RESTRICTED";
					case STATUS_OUT_OF_SERVICE -> "STATUS OUT OF SERVICE";
					case STATUS_DOWN -> "SERVICE OFFLINE";
					case STATUS_UNKNOWN -> "STATUS UNKNOWN";
					default -> "UNKNOWN STATUS";
				};
				String message = String.format(
						"Instance Status Change: Status name [%s], registration name [%s], instance ID [%s], status [%s], service URL [%s]",
						statusName, registName, instanceId, status, serviceUrl);
				
				log.info(message);
			}
		});
	}
}
