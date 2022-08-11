package constant;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Function;

public class CertificationMethod {

    public static void main(String[] args) {
        doStaticFinalMethod(CertificationMethodConstant.ID_CERTIFICATION);
        doEnumMethod(CertificationMethodEnum.MOBILE_CERTIFICATION);
    }

    public static void doStaticFinalMethod(String certificationMethodConstant) {
        if (certificationMethodConstant.equals(CertificationMethodConstant.ID_CERTIFICATION)) {
            // 넘어온 값에 맞는 로직을 실행할 수 있음
        }
        certificationMethodConstant = "존재하지않는인증값";
        // 파라미터가 string으로 넘어왔기 때문에 존재하지않는 상수값으로 교체될 수 있음
    }

    public static void doEnumMethod(CertificationMethodEnum certificationMethodEnum) {
        certificationMethodEnum = CertificationMethodEnum.MOBILE_CERTIFICATION;
        certificationMethodEnum = CertificationMethodEnum.ID_CERTIFICATION;
    }
}

class CertificationMethodConstant {
    public static final String ID_CERTIFICATION = "신분증인증";
    public static final String MOBILE_CERTIFICATION = "핸드폰인증";
}

enum CertificationMethodEnum {
    ID_CERTIFICATION("신분증인증", "I", () -> {
        // 신분증 인증 로직
        return true;
    })
    , MOBILE_CERTIFICATION("핸드폰인증", "M", () -> {
        // 핸드폰 인증 로직
        return true;
    });

    private final String context;
    private final String code;
    private final BooleanSupplier execute;

    CertificationMethodEnum(String context, String code, BooleanSupplier execute) {
        this.context = context;
        this.code = code;
        this.execute = execute;
    }

    public String getContext() {
        return context;
    }

    public String getCode() {
        return code;
    }

    public boolean executeLogic() {
        return execute.getAsBoolean();
    }
}

enum CertificationGroup {
    ID_CERTIFICATION("신분증인증", Arrays.asList(CertificationWay.ID, CertificationWay.LICENSE)),
    MOBILE_CERTIFICATION("핸드폰인증", Arrays.asList(CertificationWay.SKT, CertificationWay.KT, CertificationWay.LG)),
    EMPTY("EMPTY", Collections.EMPTY_LIST),
    ;

    private final String description;
    private final List<CertificationWay> certificationWayList;

    CertificationGroup(String description, List<CertificationWay> certificationWayList) {
        this.description = description;
        this.certificationWayList = certificationWayList;
    }

    public static CertificationGroup findByCertificationWay(CertificationWay certificationWay) {
        return Arrays.stream(CertificationGroup.values())
                .filter(certificationGroup -> certificationGroup.hasCertificationWay(certificationWay))
                .findAny()
                .orElse(EMPTY);
    }

    private boolean hasCertificationWay(CertificationWay certificationWay) {
        return certificationWayList.stream()
                .anyMatch(way -> way.equals(certificationWay));
    }
}

enum CertificationWay {
    ID("주민등록증"),
    LICENSE("운전면허증"),
    SKT("SKT"),
    KT("KT"),
    LG("LG");

    private final String description;

    CertificationWay(String description) {
        this.description = description;
    }
}
