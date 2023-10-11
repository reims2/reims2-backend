// package org.pvh.model.validator;

// import java.lang.annotation.ElementType;
// import java.lang.annotation.Retention;
// import java.lang.annotation.RetentionPolicy;
// import java.lang.annotation.Target;

// import jakarta.validation.Constraint;
// import jakarta.validation.Payload;

// @Target(ElementType.FIELD)
// @Retention(RetentionPolicy.RUNTIME)
// @Constraint(validatedBy = EnumValidator.class)
// public @interface IEnumValidator {
//     String message() default "{org.pvh.model.validator.message}";
//     Class<?>[] groups() default {};
//     Class<? extends Payload>[] payload() default {};
//     Class<? extends Enum<?>> targetClassType();
// }