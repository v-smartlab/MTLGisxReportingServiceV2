package th.com.muangthai.mtlgisxreportingservice.util;

import java.util.List;
import java.util.Map;

public class VsmUtil {

    public static boolean isEmpty(Object object) {
        if (object == null || "".equals(object.toString().trim()) || "null".equals(object.toString().trim())) {
            return true;
        }
        return false;
    }

    public static boolean isEmptyUseSpaces(Object object) {
        if (object == null || "".equals(object.toString()) || "null".equals(object.toString().trim())) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    public static boolean isNotEmptyUseSpaces(Object object) {
        return !isEmptyUseSpaces(object);
    }

    public static boolean isNotEmptyLst(List object) {
        if (null != object && object.size() > 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmptyLst(Object[] object) {
        try {
            if (null != object && object.length > 0) {
                return true;
            }
        }catch (Exception e){}

        return false;
    }

    public static boolean isEmptyLst(List object) {
        if (null == object) {
            return true;
        }

        if (object.size() <= 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmptyLst(Object[] object) {
        if (null == object) {
            return true;
        }

        if (object.length <= 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmptyLst(Map object) {
        if (null == object) {
            return true;
        }

        if (object.size() <= 0) {
            return true;
        }
        return false;
    }
}
