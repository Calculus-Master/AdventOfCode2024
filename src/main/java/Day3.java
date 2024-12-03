import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3
{
	public static void main(String[] args)
	{
		String content = "";
		try
		{
			content = new String(Files.readAllBytes(Paths.get("src/main/resources/day3.txt")));
		} catch (java.io.IOException e) { e.printStackTrace(); }

		String regex = "(mul\\([0-9]+,[0-9]+\\))|(don't\\(\\)|do\\(\\))";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);

		boolean enabled = true;
		int sum = 0;
		while (matcher.find())
		{
			String expr = matcher.group();

			if(expr.equals("don't()"))
				enabled = false;
			else if(expr.equals("do()"))
				enabled = true;
			else if(enabled)
			{
				int num1 = Integer.parseInt(expr.substring("mul(".length(), expr.indexOf(',')));
				int num2 = Integer.parseInt(expr.substring(expr.indexOf(',') + 1, expr.length() - 1));

				sum += num1 * num2;
			}
		}
		System.out.println(sum);
	}
}
