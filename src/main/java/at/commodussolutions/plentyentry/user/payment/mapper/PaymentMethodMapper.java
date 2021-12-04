package at.commodussolutions.plentyentry.user.payment.mapper;

import at.commodussolutions.plentyentry.user.payment.beans.PaymentMethod;
import at.commodussolutions.plentyentry.user.payment.dto.PaymentMethodDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {

    PaymentMethodDTO mapToListDTO(PaymentMethod entity);

    PaymentMethod mapToEntity(PaymentMethodDTO dto);
}
