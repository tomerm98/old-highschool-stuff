using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;
namespace Trump_s_Console_Helper
{
    class Program
    {
        const string connString = "Data Source=.\\SQLEXPRESS;AttachDbFilename=\"C:\\Users\\user1\\Documents\\GitHub\\SchoolWeb\\Trump's Console Helper\\Trump's Console Helper\\Database1.mdf\";Integrated Security=True;User Instance=True";
        static SqlConnection connection;
        static void Main(string[] args)
        {
            connection = new SqlConnection(connString);
            connection.Open();
            string s;
            GreetTrump();
            while ((s =Console.ReadLine()) != "quit")
            {
                switch (s)
                {
                    case "insert reporter":
                        InsertReporter();
                        break;
                    case "insert news":
                        InsertNews();
                        break;
                    case "list reporters":
                        ListReporters();
                        break;
                    case "list news by id":
                        ListNewsById();
                        break;
                    case "find reporter by news":
                        FindReporterByNews();
                        break;
                    default:
                        Console.WriteLine("UNRECONGNIZED COMMAND! REBOOTING");
                        break;

                }

                GreetTrump();
            }
            Terminate();

        }

        static void FindReporterByNews()
        {
            throw new NotImplementedException();
        }

        static void ListNewsById()
        {
            throw new NotImplementedException();
        }

        static void ListReporters()
        {
            
        }

        static void InsertNews()
        {
           
        }
        static void GreetTrump()
        {
            Console.WriteLine("Hello mr. president. What would you like to do?");
        }
        static void Terminate()
        {
            connection.Close();
        }

        static void InsertReporter()
        {

        }
    }
}
