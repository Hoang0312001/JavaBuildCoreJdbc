package com.MyBatis.mssql.dao;



import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class CommonDao<T> {
    protected Connection conn;
    private PreparedStatement ps = null;
    private CallableStatement csmt = null;
    private ResultSet rs = null;

    private final String URL = "jdbc:sqlserver://DESKTOP-EB8V35V:1433;databaseName=QLSV";
    private final String USERNAME = "sa";
    private final String PASSWORD = "1234";
    public CommonDao(){}
    public  void Close(Connection con){
        if (con != null){
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public void Close(PreparedStatement ps) {
        if (ps != null){
            try{
                ps.close();
            }catch (Exception e){
                e.getMessage();
            }
        }
    }

    public void Close(ResultSet rs){
        if (rs != null){
            try{
                rs.close();
            }catch (Exception e){
                e.getMessage();
            }
        }
    }
    public void Close(CallableStatement cs){
        if (cs != null){
            try {
                cs.close();
            }catch (Exception e){
                e.getMessage();
            }
        }
    }
    public Connection GetConnection (){
        try {
             conn = DriverManager.getConnection(URL , USERNAME , PASSWORD);
            return  conn;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public String SqlMessageWarning (SQLWarning sqlWarning){
        if (sqlWarning != null){
            return sqlWarning.getMessage();
        }
        return null;
    }
    public String WORKTYPE(String query) {
        String queryType  = query.substring(0,query.indexOf(" "));

        if (queryType.length() > 0){
            queryType.toUpperCase();
            return queryType;
        }
        return  null;
    }
    public void Parameter (Map.Entry<String , Object> entry,PreparedStatement ps){
        if (entry.getValue() instanceof  Integer){

        }
        else if (entry.getValue() instanceof  String ){

        }
        else if (entry.getValue() instanceof  Float){

        }
        else if (entry.getValue() instanceof Date){

        }
        else if (entry.getValue() instanceof  Double){

        }
    }
    public  <T> T executeQuery (String query , Map<String, Object> parameters ){
        String queryType = WORKTYPE(query);
        try(Connection conn = GetConnection()){
            ps = conn.prepareStatement(query);
            for (Map.Entry<String , Object> entry : parameters.entrySet()){

            }
            if (queryType.equals("INSERT") || queryType.equals("UPDATE")){

            }else {

            }
        }
        catch (SQLException e){
             e.getMessage();
        }
        return  null;
    }
    public  <T> T executeStorePredure(String procedureName ,Object clazz,Class<T> returnType){
        try (Connection connection = GetConnection()){
            Map<String , Object> parameters = new HashMap<>();
            StringBuilder  query = new StringBuilder("call " + " ").append(procedureName + " ");
             csmt = connection.prepareCall(query.toString());
            Field [] fields = clazz.getClass().getDeclaredFields();
            for (Field field : fields){
                parameters.put(field.getName(),field.get(clazz));
            }
            if (parameters != null){
                for (Map.Entry<String,Object > entry : parameters.entrySet()){
                    csmt.setObject(entry.getKey() ,entry.getValue());
                }
            }
             rs = csmt.executeQuery();
            if (SqlMessageWarning(rs.getWarnings()) != null){
                return (T) SqlMessageWarning(rs.getWarnings());
            }
            ResultSetMetaData metaData = rs.getMetaData();
            int column = metaData.getColumnCount();
            if (returnType.equals(ArrayList.class) || returnType.equals(Object.class)){
                List<Map<String , Object>> list = new ArrayList<>();

                while (rs.next()){
                    Map<String , Object > ob = new HashMap<>();
                    for (int i = 1 ; i <= column;i++){
                        ob.put(metaData.getColumnLabel(i) , rs.getObject(i));
                    }
                    list.add(ob);
                    if (list != null){
                        if (list.size() > 0){
                            return (T) list;
                        }
                        return  (T) list.get(0);
                    }
                    return null;
                }

            }
            if (returnType.equals(Void.class)){
                return (T) Integer.valueOf(column);
            }

        }
        catch (SQLException e ){
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            Close(rs);
            Close(csmt);
            Close(conn);
        }
        return  null;
    }
}
