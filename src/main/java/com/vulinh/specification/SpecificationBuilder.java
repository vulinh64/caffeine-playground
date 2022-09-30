package com.vulinh.specification;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.metamodel.SingularAttribute;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

/**
 * E stands for 'Entity'
 * F stands for 'Field'
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpecificationBuilder {

  public static final char BACKSLASH = '\\';

  public static <E> Specification<E> eq(String attribute, Object value) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(attribute), value);
  }

  public static <E, F> Specification<E> eq(SingularAttribute<? super E, ? extends F> attribute, F value) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(attribute), value);
  }

  public static <E> Specification<E> neq(String attribute, Object value) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get(attribute), value);
  }

  public static <E, F> Specification<E> neq(SingularAttribute<? super E, ? extends F> attribute, F value) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get(attribute), value);
  }

  public static <E, F extends Comparable<? super F>> Specification<E> ge(String attribute, F value) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get(attribute), value);
  }

  public static <E, F extends Comparable<? super F>> Specification<E> ge(SingularAttribute<? super E, ? extends F> attribute, F value) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get(attribute), value);
  }

  public static <E, F extends Comparable<? super F>> Specification<E> le(String attribute, F value) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get(attribute), value);
  }

  public static <E, F extends Comparable<? super F>> Specification<E> le(SingularAttribute<? super E, ? extends F> attribute, F value) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get(attribute), value);
  }

  public static <E, F extends Comparable<? super F>> Specification<E> geq(String attribute, F value) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(attribute), value);
  }

  public static <E, F extends Comparable<? super F>> Specification<E> geq(SingularAttribute<? super E, ? extends F> attribute, F value) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(attribute), value);
  }

  public static <E, F extends Comparable<? super F>> Specification<E> leq(String attribute, F value) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(attribute), value);
  }

  public static <E, F extends Comparable<? super F>> Specification<E> leq(SingularAttribute<? super E, ? extends F> attribute, F value) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(attribute), value);
  }

  public static <E> Specification<E> in(String attribute, Collection<?> values) {
    return (root, query, criteriaBuilder) -> root.get(attribute).in(values);
  }

  public static <E, F> Specification<E> in(SingularAttribute<? super E, ? extends F> attribute, Collection<? extends F> values) {
    return (root, query, criteriaBuilder) -> root.get(attribute).in(values);
  }

  public static <E> Specification<E> ni(String attribute, Collection<?> values) {
    return Specification.not(in(attribute, values));
  }

  public static <E, F> Specification<E> ni(SingularAttribute<? super E, ? extends F> attribute, Collection<? extends F> values) {
    return Specification.not(in(attribute, values));
  }

  public static <E> Specification<E> like(String attribute, String value) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(attribute), value, BACKSLASH);
  }

  @SuppressWarnings("unchecked")
  public static <E, F> Specification<E> like(SingularAttribute<? super E, ? extends F> attribute, String value) {
    if (String.class.isAssignableFrom(attribute.getJavaType())) {
      return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get((SingularAttribute<? super E, String>) attribute), value, '\\');
    }

    return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(attribute).as(String.class), value, '\\');
  }

  public static <E> Specification<E> isNull(String attribute) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.isNull(root.get(attribute));
  }

  public static <E> Specification<E> nonNull(String attribute) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.isNotNull(root.get(attribute));
  }

  public static <E, F extends Comparable<? super F>> Specification<E> between(String attribute, F fromValue, F toValue) {
    if (fromValue.compareTo(toValue) > 0) {
      throw new IllegalArgumentException(String.format("fromValue [%s] is greater than toValue [%s]", fromValue, toValue));
    }

    return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get(attribute), fromValue, toValue);
  }

  public static <E, F extends Comparable<? super F>> Specification<E> between(SingularAttribute<? super E, ? extends F> attribute, F fromValue, F toValue) {
    if (fromValue.compareTo(toValue) > 0) {
      throw new IllegalArgumentException(String.format("fromValue [%s] is greater than toValue [%s]", fromValue, toValue));
    }

    return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get(attribute), fromValue, toValue);
  }
}
