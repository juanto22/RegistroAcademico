package sv.com.registroacademico.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import org.apache.commons.lang.time.DateUtils;
import org.mvel2.MVEL;
import org.springframework.util.NumberUtils;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.TemporalExpression;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;
import sv.com.registroacademico.util.enumeration.OperationType;
import sv.com.registroacademico.util.enumeration.OperatorType;
import sv.com.registroacademico.config.BaseService;

import lombok.extern.java.Log;

@Log
public class AbsSpec {

	protected static Predicate toPredicate(String expression, Map<String, Object> vars) {
        return (Predicate) MVEL.eval(expression, vars);
    }

    protected static Predicate[] toPredicate(Collection<Predicate> collection) {
        if (collection.isEmpty()) {
            return null;
        }
        return (Predicate[]) collection.toArray(new Predicate[collection.size()]);
    }

    public static BooleanBuilder builder(Map<String, Object> vars, Set<ValueHolder> conditions) {
        BooleanBuilder bb = new BooleanBuilder();
        OperatorType lastOperator = OperatorType.AND;
        for (final ValueHolder item : conditions) {
            String condition = toCondition(item, vars);
            if (condition != null) {
                Predicate pred = toPredicate(condition, vars);
                if (lastOperator != null) {
                    if (OperatorType.AND.equals(lastOperator)) {
                        bb.and(pred);
                    } else if (OperatorType.OR.equals(lastOperator)) {
                        bb.or(pred);
                    }
                } else {
                    bb.and(pred);
                }

                if (item.getOperatorType() != null) {
                    lastOperator = item.getOperatorType();
                } else {
                    lastOperator = OperatorType.AND;
                }
            }
        }
        return bb;
    }

    public static BooleanBuilder builder(Map<String, Object> vars, GroupedFilter groupedFilters) {
        BooleanBuilder bb = new BooleanBuilder();
        if (groupedFilters != null && !groupedFilters.isEmpty()) {
            bb.and(groupedFilters.getBooleanBuilder(vars));
        }
        return bb;
    }

    public static Set<JPAQueryJoin> innerJoins(Collection<ValueHolder> conditions) {
        Set<JPAQueryJoin> ret = new LinkedHashSet<>();
        if (conditions != null && !conditions.isEmpty()) {
            for (ValueHolder con : conditions) {
                if (con.getInnerJoins() != null) {
                    ret.addAll(con.getInnerJoins());
                }
            }
        }
        return ret;
    }

    public static Set<JPAQueryJoin> leftJoins(Collection<ValueHolder> conditions) {
        Set<JPAQueryJoin> ret = new LinkedHashSet<>();
        if (conditions != null && !conditions.isEmpty()) {
            for (ValueHolder con : conditions) {
                if (con.getLeftJoins() != null) {
                    ret.addAll(con.getLeftJoins());
                }
            }
        }
        return ret;
    }

    public static Set<JPAQueryJoin> rightJoins(Collection<ValueHolder> conditions) {
        Set<JPAQueryJoin> ret = new LinkedHashSet<>();
        if (conditions != null && !conditions.isEmpty()) {
            for (ValueHolder con : conditions) {
                if (con.getRightJoins() != null) {
                    ret.addAll(con.getRightJoins());
                }
            }
        }
        return ret;
    }

    public static Set<JPAQueryJoin> innerJoins(GroupedFilter groupedFilters, Set<ValueHolder> conditions) {
        Set<JPAQueryJoin> ret = new LinkedHashSet<>();
        if (groupedFilters != null && !groupedFilters.isEmpty()) {
            for (GroupedFilter gf : groupedFilters.getGroupedFilterSet()) {
                ret.addAll(innerJoins(gf.getValueHolderSet()));
            }
            ret.addAll(innerJoins(groupedFilters.getValueHolderSet()));
        }
        ret.addAll(innerJoins(conditions));
        return ret;
    }

    public static Set<JPAQueryJoin> leftJoins(GroupedFilter groupedFilters, Set<ValueHolder> conditions) {
        Set<JPAQueryJoin> ret = new LinkedHashSet<>();
        if (groupedFilters != null && !groupedFilters.isEmpty()) {
            for (GroupedFilter gf : groupedFilters.getGroupedFilterSet()) {
                ret.addAll(leftJoins(gf.getValueHolderSet()));
            }
            ret.addAll(leftJoins(groupedFilters.getValueHolderSet()));
        }
        ret.addAll(leftJoins(conditions));
        return ret;
    }

    public static Set<JPAQueryJoin> rightJoins(GroupedFilter groupedFilters, Set<ValueHolder> conditions) {
        Set<JPAQueryJoin> ret = new LinkedHashSet<>();
        if (groupedFilters != null && !groupedFilters.isEmpty()) {
            for (GroupedFilter gf : groupedFilters.getGroupedFilterSet()) {
                ret.addAll(rightJoins(gf.getValueHolderSet()));
            }
            ret.addAll(rightJoins(groupedFilters.getValueHolderSet()));
        }
        ret.addAll(rightJoins(conditions));
        return ret;
    }

    public static BooleanBuilder builder(Map<String, Object> vars, boolean any, boolean custom, Set<ValueHolder> conditions, GroupedFilter groupedFilters, String... raws) {
        BooleanBuilder bb = new BooleanBuilder();
        Collection<Predicate> predicates = new ArrayList<>();
        String condition;
        if (groupedFilters != null && !groupedFilters.isEmpty()) {
            bb.and(groupedFilters.getBooleanBuilder(vars));
        }
        for (final ValueHolder item : conditions) {
            condition = toCondition(item, vars);
            if (condition != null) {
                Predicate pred = toPredicate(condition, vars);
                predicates.add(pred);
                if (custom) {
                    if (item.getOperatorType() != null) {
                        if (item.getOperatorType().equals(OperatorType.AND)) {
                            bb.and(pred);
                        } else if (item.getOperatorType().equals(OperatorType.OR)) {
                            bb.or(pred);
                        }
                    } else {
                        bb.and(pred);
                    }
                }
            }
        }

        // Fijos
        for (String raw : raws) {

        }

        for (String expression : raws) {

            Predicate predicatedFixed = toPredicate(expression, vars);
            if (predicatedFixed != null) {
                bb.and(predicatedFixed);
            }
        }
        if (!predicates.isEmpty()) {
            if (!custom) {
                if (any) {
                    bb.andAnyOf(toPredicate(predicates));
                } else {
                    BooleanBuilder or = new BooleanBuilder();
                    or.orAllOf(toPredicate(predicates));
                    bb.and(or);
                }
            }
        }

        return bb;
    }

    public static String toCondition(final ValueHolder valueHolder, Map vars) {
        String condition = null;
        String attr;
        boolean isJoin = valueHolder.isJoin();

        if (!isJoin) {
            attr = (valueHolder.getField() != null ? !valueHolder.getField().trim().equals("") ? valueHolder.getField() + "." : "" : BaseService.ENTITY_VAR + ".") + valueHolder.getName();
        } else {
            attr = valueHolder.getName();
        }
        String oper = valueHolder.getOperator();

        try {
            // BEGIN Joins
            if (valueHolder.getInnerJoins() != null && !valueHolder.getInnerJoins().isEmpty()) {
                for (JPAQueryJoin ij : valueHolder.getInnerJoins()) {
                    vars.put(ij.alias.toString(), ij.alias);
                }
            }
            if (valueHolder.getLeftJoins() != null && !valueHolder.getLeftJoins().isEmpty()) {
                for (JPAQueryJoin ij : valueHolder.getLeftJoins()) {
                    vars.put(ij.alias.toString(), ij.alias);
                }
            }
            if (valueHolder.getRightJoins() != null && !valueHolder.getRightJoins().isEmpty()) {
                for (JPAQueryJoin ij : valueHolder.getRightJoins()) {
                    vars.put(ij.alias.toString(), ij.alias);
                }
            }
            // END Joins

            if (valueHolder.isMultiValor()) {
                // TODO: code difference?
            }
            if (valueHolder.isVigenteVigente()) {
                return valueHolder.getExpressionVigente();
            }
            if (valueHolder.isVigenteVigenteNested()) {
                return valueHolder.getExpressionVigenteNested(attr);
            }
            if (valueHolder.isVigente()) {
                vars.put("ovalue", valueHolder.getFecha1());
                return valueHolder.getFilterVigencia();
            }
            if(valueHolder.isNovigente()){
                vars.put("ovalue", valueHolder.getFecha1());
                return valueHolder.getFilterNoVigente();                
            }
            Object attrib = MVEL.eval(attr, vars);
            if (attrib instanceof TemporalExpression) {
                Class attrtype = ((TemporalExpression) attrib).getType();
                if (attrtype.equals(java.util.Date.class)) {
                    if (valueHolder.getFecha1() != null) {
                        vars.put("ovalue", valueHolder.getFecha1());
                        if (oper.trim().equals(OperationType.BETWEEN.getCode())) {
                            vars.put("ovalue2", valueHolder.getFecha2());
                            condition = attr + "." + oper + "(ovalue,ovalue2)";
                        } else {
                            condition = attr + "." + oper + "(ovalue)";
                        }
                    } else if (valueHolder.getValue() != null) {
                        boolean isvalid = false;
                        try {
                            Date odate = DateUtils.parseDate(valueHolder.getValue().toString(), new String[]{"dd/MM/yyyy"});
                            vars.put("ovalue", odate);
                            isvalid = true;
                        } catch (ParseException ex) {
                            // nothing
                        }
                        if (isvalid) {
                            vars.put("resultingClass", Date.class);
                            condition = "com.mysema.query.types.template.DateTemplate.create(resultingClass, \"trunc({0})\", " + attr + ").eq(ovalue)";
                        } else {
                            vars.put("ovalue", valueHolder.getValue().toString());
                            vars.put("pattern", "dd/MM/yyyy");
                            condition = "com.mysema.query.types.template.StringTemplate.create(\"to_char({0},'{1s}')\", "
                                    + attr
                                    + ", com.mysema.query.types.ConstantImpl.create(pattern)).like("
                                    + "com.mysema.query.support.Expressions.stringTemplate(\"'%'\").concat(com.mysema.query.support.Expressions.stringTemplate(\"{0}\" , ovalue )).concat(com.mysema.query.support.Expressions.stringTemplate(\"'%'\"))"
                                    + ")";
                        }
                    } else if (oper.trim().equals(OperationType.NULL.getCode()) || oper.trim().equals(OperationType.NOT_NULL.getCode())) {
                        condition = attr + "." + oper + "()";
                    }

                } else if (attrtype.equals(java.util.Calendar.class)) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(valueHolder.getFecha1());
                    vars.put("ovalue", cal);
                    if (oper.trim().equals(OperationType.BETWEEN.getCode())) {
                        Calendar cal2 = Calendar.getInstance();
                        cal2.setTime(valueHolder.getFecha2());
                        vars.put("ovalue2", cal2);
                        condition = attr + "." + oper + "(ovalue,ovalue2)";
                    } else {
                        condition = attr + "." + oper + "(ovalue)";
                    }
                }
            } else if (oper.trim().equals(OperationType.NULL.getCode()) || oper.trim().equals(OperationType.NOT_NULL.getCode())) {
                condition = attr + "." + oper + "()";
            } else if (oper.trim().equals(OperationType.IN.getCode())) {
                Object[] arrayValue = null;
                if (valueHolder.getValue() instanceof Object[]) {
                    arrayValue = (Object[]) valueHolder.getValue();
                } else if (valueHolder.getValue() != null && Collection.class.isAssignableFrom(valueHolder.getValue().getClass())) {
                    Collection coll = (Collection) valueHolder.getValue();
                    if (attrib instanceof NumberPath) {
                        arrayValue = new Number[coll.size()];
                    } else {
                        arrayValue = new Object[coll.size()];
                    }
                    arrayValue = coll.toArray(arrayValue);
                } else if (valueHolder.getValue() instanceof String && ((String) valueHolder.getValue()).contains(",")) {
                    if (attrib instanceof NumberPath) {
                        String[] sarrayValue = ((String) valueHolder.getValue()).split("\\,");
                        if (sarrayValue != null && sarrayValue.length > 0) {
                            arrayValue = new Number[sarrayValue.length];
                            for (int i = 0; i < sarrayValue.length; i++) {
                                arrayValue[i] = NumberUtils.parseNumber(sarrayValue[i], Number.class);
                            }
                        }
                    } else {
                        arrayValue = ((String) valueHolder.getValue()).split("\\,");
                    }
                } else if (valueHolder.getValue() instanceof String) {
                    if (attrib instanceof NumberPath) {
                        String svalue = valueHolder.getValue().toString();
                        if (svalue != null && !svalue.trim().equals("")) {
                            arrayValue = new Number[]{NumberUtils.parseNumber(valueHolder.getValue().toString(), Number.class)};
                        } else {
                            arrayValue = new Number[]{-666};
                        }
                    } else {
                        arrayValue = new String[]{valueHolder.getValue().toString()};
                    }
                } else {
                    arrayValue = new Object[]{valueHolder.getValue()};
                }
                vars.put("arrayValue", arrayValue);
                condition = attr + "." + oper + "(arrayValue)";
            } else if (oper.trim().equals(OperationType.NOT_IN.getCode())) {
                if (valueHolder.getValue() instanceof Collection) {
                    vars.put("arrayValue", (valueHolder.getValue()));
                    condition = attr + "." + oper + "(arrayValue)";
                }
            } else if (oper.trim().equals(OperationType.BETWEEN.getCode())) {
                condition = attr + "." + oper + "(" + valueHolder.getValue() + "," + valueHolder.getValue2() + ")";
            } else if (oper.trim().equals(ValueHolder._ILIKE)) {
                if (attrib instanceof StringPath) {
                    condition = attr + ".upper." + oper + "(\"%" + valueHolder.getValue().toString().toUpperCase() + "%\")";
                } else if (attrib instanceof NumberPath) {
                    condition = attr + ".eq(" + valueHolder.getValue().toString() + ")";
                } else {
                    condition = attr + "." + oper + "(\"" + valueHolder.getValue().toString().toUpperCase() + "\")";
                }
            } else if (attrib instanceof StringPath) {
                condition = attr + "." + oper + "(\"" + valueHolder.getValue() + "\")";
            } else {
                condition = attr + "." + oper + "(" + valueHolder.getValue() + ")";
            }
        } catch (Exception ex) {
            log.log(Level.OFF, null, ex);
        }
        return condition;
    }

}
