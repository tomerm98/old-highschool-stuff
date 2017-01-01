using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace sqlExcercise
{
    class Program
    {
        const string connectionString = "Data Source=(LocalDB)\\MSSQLLocalDB;AttachDbFilename=C:\\Users\\Tomer\\Documents\\GitHub\\SchoolWeb\\sqlExcercise\\sqlExcercise\\Database.mdf;Integrated Security=True";
        const int  NAME_INDEX = 0;
        const int COMPOSER_INDEX = 1;
        const int YEAR_INDEX = 2;
        
        static void Main(string[] args)
        {
            
            PrintAllSongs();
            Console.ReadKey();
        }
        static void AddSong(string name, string composer, int year)
        {
            string query = String.Format("INSERT INTO Songs VALUES ('{0}', '{1}', '{2}');",name,composer,year);
            SqlConnection connection = new SqlConnection(connectionString);
            connection.Open();
            SqlCommand command = connection.CreateCommand();
            command.CommandText = query;
            command.ExecuteNonQuery();
            connection.Close();
        }
        
        static void PrintAllSongs()
        {
            string query = "SELECT * FROM Songs;";

            SqlConnection connection = new SqlConnection(connectionString);
            connection.Open();
            SqlCommand command = connection.CreateCommand();
            command.CommandText = query;
            SqlDataReader reader = command.ExecuteReader();
            while (reader.Read())
            {
                Console.WriteLine("Song name: " + reader.GetString(NAME_INDEX));
                Console.WriteLine("Composer name: " + reader.GetString(COMPOSER_INDEX));
                Console.WriteLine("Year: " + reader.GetInt32(YEAR_INDEX));
                Console.WriteLine("---------------------------------");
            }
            connection.Close();
        }

      
      
        
    }
}
