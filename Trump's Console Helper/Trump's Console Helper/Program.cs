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
        const int NEWS_ID_INDEX = 0;
        const int NEWS_TITLE_INDEX = 1;
        const int NEWS_REPORTED_ID_INDEX = 2;
        const int REPORTERS_ID_INDEX = 0;
        const int REPORTERS_FNAME_INDEX = 1;
        const int REPORTERS_LNAME_INDEX = 2;
        const int REPORTERS_ADDRESS_INDEX = 3;

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
            string id, fname, lname, address;
            string query = String.Format("SELECT * FROM Reporters");
            SqlDataReader reader = ExecuteReader(query);
            while (reader.Read())
            {
                id = reader.GetString(REPORTERS_ID_INDEX);
                fname = reader.GetString(REPORTERS_FNAME_INDEX);
                lname = reader.GetString(REPORTERS_LNAME_INDEX);
                address = reader.GetString(REPORTERS_ADDRESS_INDEX);
                Console.WriteLine("**********");
                PrintReporter(id, fname, lname, address);
               
            }
        }
        static void PrintReporter(string id, string fname, string lname, string address)
        {
            Console.WriteLine("ID: " + id);
            Console.WriteLine("NAME: " + fname + " " + lname);
            Console.WriteLine("ADDRESS: " + address);
        }

        static void InsertNews()
        {
            Console.WriteLine("Please Enter a Title");
            string title = Console.ReadLine();
            Console.WriteLine("Please Enter a Reporter Id");
            string reporterId = Console.ReadLine();
            string query = String.Format("INSERT INTO News VALUES ({0},{1})", title, reporterId);
            ExecuteNonQuery(query);
            Console.WriteLine("News Inserted");
            Console.WriteLine();
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
            Console.WriteLine("Please Enter a First Name");
            string fname = Console.ReadLine();
            Console.WriteLine("Please Enter a Last Name");
            string lname = Console.ReadLine();
            Console.WriteLine("Please Enter an Address");
            string address = Console.ReadLine();
            string query = String.Format("INSERT INTO Reporters VALUES ({0}, {1}, {2});", fname, lname, address);
            ExecuteNonQuery(query);
            Console.WriteLine("Reporter Inserted");
            Console.WriteLine();
        }



        static SqlDataReader ExecuteReader(string query)
        {
            SqlCommand command = connection.CreateCommand();
            command.CommandText = query;
            return command.ExecuteReader();
        }

        static void ExecuteNonQuery(string query)
        {
            SqlCommand command = connection.CreateCommand();
            command.CommandText = query;
            command.ExecuteNonQuery();
        }
    }
}
