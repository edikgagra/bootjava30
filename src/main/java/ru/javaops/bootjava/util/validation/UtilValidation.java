package ru.javaops.bootjava.util.validation;

import lombok.experimental.UtilityClass;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.lang.NonNull;
import ru.javaops.bootjava.HasId;
import ru.javaops.bootjava.exception.IllegalRequestException;

@UtilityClass
public class UtilValidation {

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestException(bean.getClass().getSimpleName() + " must be new (id=null)");
        }
    }


    public static void assureIdConsistent(HasId bean, int id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.id() != id) {
            throw new IllegalRequestException(bean.getClass().getSimpleName() + " must has id=" + id);
        }
    }

    public static void checkModification(int count, int id) {
        if (count == 0) {
            throw new IllegalRequestException("Entity with id=" + id + " not found");
        }
    }
    public static <T> T checkExisted(T obj, int id) {
        if (obj == null) {
            throw new IllegalRequestException("Entity with id=" + id + " not found");
        }
        return obj;
    }


}