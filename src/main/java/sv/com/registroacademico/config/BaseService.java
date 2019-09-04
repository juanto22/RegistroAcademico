package sv.com.registroacademico.config;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Order;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.PathBuilder;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import sv.com.registroacademico.config.BaseEntity;
import sv.com.registroacademico.util.AbsSpec;
import sv.com.registroacademico.util.GroupedFilter;
import sv.com.registroacademico.util.JPAQueryJoin;
import sv.com.registroacademico.util.ValueHolder;

@Getter
@Setter
public abstract class BaseService<T extends BaseEntity<ID>, ID extends Serializable> extends CrudService<T, ID> {

    protected boolean distinct = false;

    public Page<T> findAll(int first, int pageSize, List<Sort.Order> orders, boolean any, boolean custom, Set<ValueHolder> conditions, GroupedFilter groupedFilters, String... raws) {
        return findAll(first, pageSize, orders, any, custom, conditions, groupedFilters, distinct, raws);
    }

    public Page<T> findAll(int first, int pageSize, List<Sort.Order> orders, boolean any, boolean custom, Set<ValueHolder> conditions, GroupedFilter groupedFilters, boolean distinct, String... raws) {
        Sort sort = new Sort(orders);
        return findAll(first, pageSize, sort, any, custom, conditions, groupedFilters, distinct, raws);
    }

    public Page<T> findAll(int first, int pageSize, Sort sort, boolean any, boolean custom, Set<ValueHolder> conditions, GroupedFilter groupedFilters, boolean distinct, String... raws) {
        int page = Math.max(first / pageSize, 0);
        PageRequest pageable = new PageRequest(page, pageSize, sort);
        return findAll(pageable, any, custom, conditions, groupedFilters, distinct, raws);
    }

    public List<T> findAll(GroupedFilter groupedFilters) {
        BooleanBuilder spec = AbsSpec.builder(vars(), groupedFilters);
        return (List<T>) getRepository().findAll(spec);
    }

    public Page<T> findAll(Pageable pageable, boolean any, boolean custom, Set<ValueHolder> conditions, GroupedFilter groupedFilters, String... raws) {
        return findAll(pageable, any, custom, conditions, groupedFilters, distinct, raws);
    }

    // Used by BaseLazyModel
    public Page<T> findAll(Pageable pageable, boolean any, boolean custom, Set<ValueHolder> conditions, GroupedFilter groupedFilters, boolean bdistinct, String... raws) {
        BooleanBuilder spec = AbsSpec.builder(vars(), any, custom, conditions, groupedFilters, raws);
        Set<JPAQueryJoin> innerJoins = AbsSpec.innerJoins(groupedFilters, conditions);
        Set<JPAQueryJoin> leftJoins = AbsSpec.leftJoins(groupedFilters, conditions);
        Set<JPAQueryJoin> rightJoins = AbsSpec.rightJoins(groupedFilters, conditions);

        return findAll(spec, pageable, innerJoins, leftJoins, rightJoins, bdistinct);
    }

    public List<T> findAll(Set<ValueHolder> conditions) {
        BooleanBuilder spec = AbsSpec.builder(vars(), conditions);
        return (List<T>) getRepository().findAll(spec);
    }

    public List<T> findAll(Set<ValueHolder> conditions, Sort sort) {
        BooleanBuilder spec = AbsSpec.builder(vars(), conditions);
        return (List<T>) getRepository().findAll(spec, sort);
    }

    public List<T> findAll(boolean any, boolean custom, Set<ValueHolder> conditions, GroupedFilter groupedFilters, String... raws) {
        BooleanBuilder spec = AbsSpec.builder(vars(), any, custom, conditions, groupedFilters, raws);
        Set<JPAQueryJoin> innerJoins = AbsSpec.innerJoins(groupedFilters, conditions);
        Set<JPAQueryJoin> leftJoins = AbsSpec.leftJoins(groupedFilters, conditions);
        Set<JPAQueryJoin> rightJoins = AbsSpec.rightJoins(groupedFilters, conditions);
        return (List<T>) findAll(spec, innerJoins, leftJoins, rightJoins);
    }

    public Class<?> getType(Class cl, Object value) {
        return String.class;
    }

    public Page<T> findAll(Pageable pageable, boolean any, boolean custom, Set<ValueHolder> conditions, GroupedFilter groupedFilters, BooleanBuilder fixed, String... raws) {
        BooleanBuilder spec = AbsSpec.builder(vars(), any, custom, conditions, groupedFilters, raws);
        return getRepository().findAll(spec.and(fixed), pageable);
    }

    private JPAQuery joins(JPAQuery q, Set<JPAQueryJoin> innerJoins, Set<JPAQueryJoin> leftJoins, Set<JPAQueryJoin> rightJoins) {
        if (innerJoins != null && !innerJoins.isEmpty()) {
            for (JPAQueryJoin ij : innerJoins) {
                if (!ij.isEagerFetch()) {
                    q = q.innerJoin(ij.getEntityPath(), ij.getAlias());
                } else {
                    q = q.innerJoin(ij.getEntityPath(), ij.getAlias()).fetch();
                }
            }
        }

        if (leftJoins != null && !leftJoins.isEmpty()) {
            for (JPAQueryJoin ij : leftJoins) {
                if (!ij.isEagerFetch()) {
                    q = q.leftJoin(ij.getEntityPath(), ij.getAlias());
                } else {
                    q = q.leftJoin(ij.getEntityPath(), ij.getAlias()).fetch();
                }
            }
        }

        if (rightJoins != null && !rightJoins.isEmpty()) {
            for (JPAQueryJoin ij : rightJoins) {
                if (!ij.isEagerFetch()) {
                    q = q.rightJoin(ij.getEntityPath(), ij.getAlias());
                } else {
                    q = q.rightJoin(ij.getEntityPath(), ij.getAlias()).fetch();
                }
            }
        }
        return q;
    }

    private List<T> findAll(BooleanBuilder predicate, Set<JPAQueryJoin> innerJoins, Set<JPAQueryJoin> leftJoins, Set<JPAQueryJoin> rightJoins) {
        EntityPathBase<T> qentity = getQEntity();
        JPAQuery q = newJpaQuery().from(qentity);
        q = joins(q, innerJoins, leftJoins, rightJoins);
        q = q.where(predicate);
        return q.list(qentity);
    }

    private Page<T> findAll(BooleanBuilder predicate, Pageable pageable, Set<JPAQueryJoin> innerJoins, Set<JPAQueryJoin> leftJoins, Set<JPAQueryJoin> rightJoins, boolean distinct) {
        EntityPathBase<T> qentity = getQEntity();
        PathBuilder<T> entityPath = new PathBuilder<>(clazz, qentity.getMetadata());
        JPAQuery q = newJpaQuery().from(qentity);
        q = joins(q, innerJoins, leftJoins, rightJoins);
        if (distinct) {
            q = q.distinct();
        }
        q = q.where(predicate);
        long dataCount = q.count();
        q.offset(pageable.getOffset());
        q. limit(pageable.getPageSize());

        if (pageable.getSort() != null) {
            for (Sort.Order order : pageable.getSort()) {
                PathBuilder path = entityPath.get(order.getProperty());
                q = q.orderBy(new OrderSpecifier(Order.valueOf(order.getDirection().name()), path));
            }
        }
        return getPage(getPageContent(q), pageable, dataCount);
    }

    public List<T> getPageContent(JPAQuery q) {
        return q.list(getQEntity());
    }

    public static <T> Page<T> getPage(List<T> content, Pageable pageable, long dataCount) {

        if (pageable.getOffset() == 0) {
            return new PageImpl<>(content, pageable, dataCount);
        }
        if (pageable.getPageSize() > content.size()) {
            return new PageImpl<>(content, pageable, content.size());
        }

        if (!content.isEmpty() && pageable.getPageSize() > content.size()) {
            return new PageImpl<>(content, pageable, pageable.getOffset() + content.size());
        }

        return new PageImpl<>(content, pageable, dataCount);
    }
}
