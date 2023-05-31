using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.SqlClient;

/// <summary>
/// Summary description for DatabaseHelper
/// </summary>
public static class DatabaseHelper
{
    
    public static void Execute(SqlConnection conn,string query, params string[] pars)
    {  
        conn.Open();
        SqlCommand com = new SqlCommand(query, conn);
        InsertParamsToCommand(com, pars);
        com.ExecuteNonQuery();
    }
    public static SqlDataReader ExecuteForResult(SqlConnection conn, string query,params string[] pars)
    {
        conn.Open();
        SqlCommand com = new SqlCommand(query, conn);
        InsertParamsToCommand(com, pars);
        SqlDataReader reader =  com.ExecuteReader();  
        return reader;
    }
    private static void InsertParamsToCommand(SqlCommand command, params string[] pars)
    {
        if (pars.Length > 0)
            for (int i = 0; i < pars.Length; i++)
                command.Parameters.AddWithValue("@" + i.ToString(), pars[i]);
        
    }

   



}