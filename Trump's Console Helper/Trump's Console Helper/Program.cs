using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;
namespace Trump_s_Console_Helper
{
    class Program
    {
        const string connString = "Data Source=(LocalDB)\\MSSQLLocalDB;AttachDbFilename=\"C:\\Users\\Tomer\\Documents\\GitHub\\SchoolWeb\\Trump's Console Helper\\Trump's Console Helper\\Database.mdf\";Integrated Security=True";
        const int NEWS_ID_INDEX = 0;
        const int NEWS_TITLE_INDEX = 1;
        const int NEWS_REPORTER_ID_INDEX = 2;

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
                    case "list news by reporter id":
                        ListNewsByReporterId();
                        break;
                    case "find reporters by news":
                        FindReportersByNews();
                        break;
                    default:
                        Console.WriteLine("UNRECONGNIZED COMMAND! REBOOTING");
                        break;

                }

                GreetTrump();
            }
            Terminate();

        }

        static void FindReportersByNews()
        {
            Console.WriteLine("Please enter a news title");
            string newsTitle = Console.ReadLine();
            string titleQuery = "SELECT ReporterId FROM News WHERE Title= '" + newsTitle + "';";
            SqlDataReader titleReader = ExecuteReader(titleQuery);
            string  reporterQuery, fname,lname,address;
            List<int> reporterIdList = new List<int>();
            SqlDataReader reporterReader;
            
            while(titleReader.Read())
            {
                reporterIdList.Add(titleReader.GetInt32(0));
            }
            titleReader.Close();

            foreach (int reporterId in reporterIdList)
            {
                reporterQuery = "SELECT * FROM Reporters WHERE Id = " + reporterId + ";";
                reporterReader = ExecuteReader(reporterQuery);
                while (reporterReader.Read())
                {
                    fname = reporterReader.GetString(REPORTERS_FNAME_INDEX);
                    lname = reporterReader.GetString(REPORTERS_LNAME_INDEX);
                    address = reporterReader.GetString(REPORTERS_ADDRESS_INDEX);
                    Console.WriteLine("********");
                    PrintReporter(reporterId, fname, lname, address);
                }
                reporterReader.Close();
            }
         
                
           
        }

        static void ListNewsByReporterId()
        {
            Console.WriteLine("Please enter reporter ID");
            int reporterId = int.Parse(Console.ReadLine());
            string query = "SELECT Id, Title FROM News WHERE ReporterId = " + reporterId + ";";
            SqlDataReader reader = ExecuteReader(query);
            int id;
            string title;
            while (reader.Read())
            {
                id = reader.GetInt32(NEWS_ID_INDEX);
                title = reader.GetString(NEWS_TITLE_INDEX);
                Console.WriteLine("**********");
                PrintNews(id, title, reporterId);
            }
            reader.Close();
        }
        static void PrintNews(int id, string title, int reporterId)
        {
            Console.WriteLine("ID: " + id);
            Console.WriteLine("Title: " + title);
            Console.WriteLine("Reporter ID: " + reporterId);
        }

        static void ListReporters()
        {
            string fname, lname, address;
            int id;
            string query = "SELECT * FROM Reporters;";
            SqlDataReader reader = ExecuteReader(query);
            while (reader.Read())
            {
                id = reader.GetInt32(REPORTERS_ID_INDEX);
                fname = reader.GetString(REPORTERS_FNAME_INDEX);
                lname = reader.GetString(REPORTERS_LNAME_INDEX);
                address = reader.GetString(REPORTERS_ADDRESS_INDEX);
                Console.WriteLine("**********");
                PrintReporter(id, fname, lname, address);
               
            }
            reader.Close();
        }
        static void PrintReporter(int id, string fname, string lname, string address)
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
            string query = string.Format("INSERT INTO News VALUES ('{0}',{1})", title, reporterId);
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
           

            string query = string.Format("INSERT INTO Reporters VALUES ('{0}', '{1}', '{2}');", fname, lname, address);
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
