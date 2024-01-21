using System.Text;
using System.Text.RegularExpressions;

namespace ccwc
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Program program = new Program();

            if (args.Length > 0 )
            {
                string filepath = "";

                if ( args.Length > 1)
                {
                    filepath = $".\\{args[1]}";
                }

                if (args[0] == "-c")
                {
                    try
                    {
                        Console.WriteLine($"{program.GetNumberOfBytesOfAFile(filepath)} {args[1]}");
                    }
                    catch { }

                }
                else if (args[0] == "-l")
                {
                    if (args.Length == 1)
                    {
                        Console.WriteLine($"{program.GetNumberOfLinesOfAFile(program.FilePath())}");
                    }
                        
                    try
                    {
                        Console.WriteLine($"{program.GetNumberOfLinesOfAFile(filepath)} {args[1]}");
                    }
                    catch { }
                    
                }
                else if (args[0] == "-w")
                {
                    try
                    {
                        Console.WriteLine($"{program.GetNumberOfWordsOfAFile(program.StringList(filepath))} {args[1]}");
                    }
                    catch { }
                }
                else if (args[0] == "-m")
                {
                    try
                    {
                        Console.WriteLine($"{program.GetNumberOfCharactersOfAFile(program.Document(filepath))} {args[1]}");
                    }
                    catch { }
                }
                else if (args[0].Contains(".txt") && args.Length == 1)
                {
                    try
                    {
                        filepath = $".\\{args[0]}";
                        Console.WriteLine($"{program.GetNumberOfLinesOfAFile(filepath)} {program.GetNumberOfWordsOfAFile(program.StringList(filepath))} {program.GetNumberOfBytesOfAFile(filepath)} {args[0]}");
                    }
                    catch { }
                }
            }

            Console.ReadLine();
        }

        private long GetNumberOfBytesOfAFile(string filepath)
        {
            long total = 0;

            try
            {
                return new FileInfo(filepath).Length;
            }
            catch { }
            
            return total;
        }

        private int GetNumberOfLinesOfAFile( string filepath )
        {
            return File.ReadAllLines(filepath).Length;
        }

        private int GetNumberOfWordsOfAFile(List<string> strings)
        {
            return strings.Count;
        }

        private int GetNumberOfCharactersOfAFile(string strings)
        {
            return strings.Count();
        }

        private List<string> StringList(string filepath)
        {
            StreamReader streamReader = new StreamReader(filepath);

            string? line;
            List<string> result = new List<string>();
            Regex regex = new Regex(@"\s");

            while ((line = streamReader.ReadLine()) != null)
            {
                line = line.TrimStart().TrimEnd();

                if (!string.IsNullOrWhiteSpace(line))
                {
                    result.AddRange(regex.Split(line));
                }
            }

            return result;
        }

        private string Document(string filepath)
        {
            StreamReader streamReader = new StreamReader(filepath);

            string? line;
            StringBuilder file = new StringBuilder();
            List<string> result = new List<string>();

            while ((line = streamReader.ReadLine()) != null)
            {
                file.AppendLine(line);
            }

            return file.ToString();
        }

        private string FilePath()
        {
            string? text = Console.In.ReadToEnd();
            string fileName = "test1.txt";

            File.WriteAllText(fileName, text);

            return $".\\{fileName}";
        }
    }
}