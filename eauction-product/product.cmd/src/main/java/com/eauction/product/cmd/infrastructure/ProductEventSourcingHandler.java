package com.eauction.product.cmd.infrastructure;

import com.eauction.cqrs.core.domain.AggregateRoot;
import com.eauction.cqrs.core.events.BaseEvent;
import com.eauction.cqrs.core.handlers.EventSourcingHandler;
import com.eauction.cqrs.core.infrastructure.EventStore;
import com.eauction.product.cmd.domain.ProductAggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductEventSourcingHandler implements EventSourcingHandler<ProductAggregate> {
    @Autowired
    private EventStore eventStore;

    @Override
    public void save(AggregateRoot aggregate) {
        eventStore.saveEvents(aggregate.getId(), aggregate.getUncommittedChanges(), aggregate.getVersion());
        aggregate.markChangesAsCommitted();
    }

    @Override
    public ProductAggregate getById(String id) {
        var aggregate = new ProductAggregate();
        var events = eventStore.getEvents(id);
        if (events != null && !events.isEmpty()) {
            aggregate.replayEvents(events);
            var latestVersion = events.stream().map(x -> x.getVersion()).max(Comparator.naturalOrder());
            aggregate.setVersion(latestVersion.get());
        }
        return aggregate;
    }

    //@Override
    public ProductAggregate getByProductId(String id) {
        var aggregate = new ProductAggregate();
        var events = eventStore.getEvents(id);
        if (events != null && !events.isEmpty()) {
            aggregate.replayEvents(events);
            var latestVersion = events.stream().map(x -> x.getVersion()).max(Comparator.naturalOrder());
            aggregate.setVersion(latestVersion.get());
        }
        return aggregate;
    }

    /*public List<ProductAggregate> getByProductIds(String id) {
        List<ProductAggregate> aggregateList = null;
        List<BaseEvent> events = eventStore.getEvents(id);
        if (events != null && !events.isEmpty()) {
            for (BaseEvent l:events) {
                aggregateList = new ArrayList<ProductAggregate>();
                aggregateList.replayEvents(events);
                var latestVersion = events.stream().map(x -> x.getVersion()).max(Comparator.naturalOrder());
                aggregate.setVersion(latestVersion.get());
            }

        }
        return aggregate;
    }*/
}
