package sv.com.registroacademico.util;

import com.mysema.query.types.Path;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.path.SetPath;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import sv.com.registroacademico.util.enumeration.OperationType;
import sv.com.registroacademico.util.enumeration.OperatorType;
import static sv.com.registroacademico.util.AbsSpec.toCondition;
import static sv.com.registroacademico.util.AbsSpec.toPredicate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(of = {"operatorType", "name", "operator", "value", "value2", "fecha1", "fecha2"})
public class ValueHolder implements Serializable {

	/**
    *
    */
   private static final long serialVersionUID = -6622720316247931219L;
   public final static String _EQ = "eq";
   public final static String _ILIKE = "like";
   public final static String _IN = "in";
   public final static String _BETWEEN = "between";

   @Getter
   @Setter
   private String name;

   @Getter
   @Setter
   private String name2;

   @Getter
   @Setter
   private String label;

   @Getter
   protected String operator; // enum

   @Setter
   @Getter
   private String operatorDescription; // enum

   @Getter
   @Setter
   //private List<Object> value = new ArrayList<Object>();
   private Object value;

   @Getter
   @Setter
   private Object value2;

   @Getter
   @Setter
   private Date fecha1;

   @Getter
   @Setter
   private Date fecha2;

   @Getter
   @Setter
   private Integer id;

   @Getter
   @Setter
   private OperatorType operatorType;

   @Getter
   @Setter
   private String field;

   @Getter
   @Setter
   private boolean vigente = false;
   
   @Getter
   @Setter
   private boolean novigente = false;

   @Getter
   @Setter
   private List<JPAQueryJoin> innerJoins;

   @Getter
   @Setter
   private List<JPAQueryJoin> leftJoins;

   @Getter
   @Setter
   private List<JPAQueryJoin> rightJoins;

   @Getter
   @Setter
   private boolean isJoin = false;

   public ValueHolder() {
   }

   public ValueHolder(String attr, Object value) {
       this.name = attr;
       this.operator = _EQ;
       this.value = value;
       //this.value.add(value);
   }

   public ValueHolder(String attr, String oper, Object value) {
       this.name = attr;
       this.operator = oper;
       //this.value.addAll(Arrays.asList(value));
       this.value = value;
   }

   public ValueHolder(String attr, String oper, Object value, OperatorType operType) {
       this.name = attr;
       this.operator = oper;
       //this.value.addAll(Arrays.asList(value));
       this.value = value;
       this.operatorType = operType;
   }

   public ValueHolder(Integer id, String attr, String oper, Object value, Object value2, String label, Date fecha1, Date fecha2) {
       this.id = id;
       this.name = attr;
       this.operator = oper;
       //this.value.addAll(Arrays.asList(value));
       this.value = value;
       this.value2 = value2;
       this.label = label;
       this.fecha1 = fecha1;
       this.fecha2 = fecha2;
   }

   public boolean isMultiValor() {
       return _IN.equals(operator) || _BETWEEN.equals(operator);
   }

   @Override
   public String toString() {
       return "Holder: " + name + " " + operator + " " + value.toString();
   }

   public void setOperator(String operator) {
       if (operator != null) {
           setOperatorDescription(OperationType.getOperationType(operator).getDescription());
       }
       this.operator = operator;
   }

   public Predicate predicate(Map<String, Object> vars) {
       String condition = toCondition(this, vars);
       return toPredicate(condition, vars);
   }

   public ValueHolder(String attr, boolean vigente, Object value) {
       this.name = attr;
       this.vigente = vigente;
       this.value = value;
   }

   public boolean isVigenteVigente() {
       return "vigente.vigente".equals(name) && vigente;
   }

   public String getExpVigente() {
       String byFinVigencia = "entity.vigente.finVigencia.after(new java.util.Date())";
       String byFinVigenciaNull = "entity.vigente.finVigencia.isNull()";
       String byIniciaVigenciaToday = "entity.vigente.iniciaVigencia.after(new java.util.Date())";
       String byIniciaVigencia = "entity.vigente.iniciaVigencia.before(new java.util.Date())";
       return byFinVigencia + ".or(" + byFinVigenciaNull + ").and(" + byIniciaVigencia + ".or(" + byIniciaVigencia + ".and(" + byIniciaVigenciaToday + ")))";
   }

   public String getExpNotVigente() {
       String byFinVigencia = "entity.vigente.finVigencia.before(new java.util.Date())";
       String byFinVigenciaNotNull = "entity.vigente.finVigencia.isNotNull()";
       return byFinVigencia + ".and(" + byFinVigenciaNotNull + ")";
   }

   public String getExpressionVigente() {
       boolean bval = ((Boolean) value);
       return bval ? getExpVigente() : getExpNotVigente();
   }

   public String getFilterVigencia() {
       String byFinVigencia = "entity." + name + ".goe(ovalue)";
       String byFinVigenciaNull = "entity." + name + ".isNull()";
       String byIniciaVigenciaToday = "entity." + name2 + ".goe(ovalue)";
       String byIniciaVigencia = "entity." + name2 + ".loe(ovalue)";
       return byFinVigencia + ".or(" + byFinVigenciaNull + ").and(" + byIniciaVigencia + ".or(" + byIniciaVigencia + ".and(" + byIniciaVigenciaToday + ")))";
   }

   public String getFilterNoVigente() {
       String byHasta = "entity." + name + ".lt(ovalue)"; // hasta
       String byDesde = "entity." + name2 + ".gt(ovalue)";//desde
       return byHasta + ".or(" + byDesde + ")";
   }

   public boolean isVigenteVigenteNested() {
       return name != null && name.contains("vigente.vigente");
   }

   public String getExpressionVigenteNested(String attr) {
       String exp = getExpressionVigente();
       String newAttr = attr.replaceAll(".vigente.vigente", ".vigente");
       return exp.replaceAll("entity.vigente", newAttr);
   }

   public void innerJoin(SetPath entityPath, Path alias) {
       innerJoin(new JPAQueryJoin(entityPath, alias));
   }

   public void innerJoin(JPAQueryJoin join) {
       if (innerJoins == null) {
           innerJoins = new ArrayList<>();
       }
       innerJoins.add(join);
   }

   public void leftJoin(SetPath entityPath, Path alias) {
       leftJoin(new JPAQueryJoin(entityPath, alias));
   }

   public void leftJoin(JPAQueryJoin join) {
       if (leftJoins == null) {
           leftJoins = new ArrayList<>();
       }
       leftJoins.add(join);
   }

   public void rightJoin(SetPath entityPath, Path alias) {
       rightJoin(new JPAQueryJoin(entityPath, alias));
   }

   public void rightJoin(JPAQueryJoin join) {
       if (rightJoins == null) {
           rightJoins = new ArrayList<>();
       }
       rightJoins.add(join);
   }

}
