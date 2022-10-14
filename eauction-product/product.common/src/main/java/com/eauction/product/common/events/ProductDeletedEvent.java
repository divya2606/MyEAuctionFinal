package com.eauction.product.common.events;

import com.eauction.cqrs.core.events.BaseEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class ProductDeletedEvent extends BaseEvent {
}
