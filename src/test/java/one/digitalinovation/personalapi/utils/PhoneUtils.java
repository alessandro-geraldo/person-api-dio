package one.digitalinovation.personalapi.utils;

import one.digitalinovation.personalapi.dto.request.PhoneDTO;
import one.digitalinovation.personalapi.entity.Phone;
import one.digitalinovation.personalapi.enums.PhoneType;

public class PhoneUtils {

    private static final String PHONE_NAMBER = "18 9885522211";
    private static final PhoneType PHONE_TYPE = PhoneType.MOBILE;
    private static final Long PHONE_ID = 1L;

    public static PhoneDTO createFakeDTO(){
        return PhoneDTO.builder()
                .number(PHONE_NAMBER)
                .type(PHONE_TYPE)
                .build();
    }

    public static Phone createFakeEntity(){
        return Phone.builder()
                .id(PHONE_ID)
                .number(PHONE_NAMBER)
                .type(PHONE_TYPE)
                .build();
    }
}
