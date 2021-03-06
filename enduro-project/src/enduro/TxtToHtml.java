package enduro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TxtToHtml {
	/**
	 * Converts a result file from excel-format to an html-table.
	 * 
	 * @param txtFile
	 * @param htmlFile
	 * @throws IOException 
	 * @throws Exception
	 */
	public void makeHtmlFile(String txtFile, String htmlFile) throws IOException  {
		PrintWriter pW = new PrintWriter(new BufferedWriter(new FileWriter(
				htmlFile)));
		BufferedReader in = new BufferedReader(new FileReader(txtFile));
		pW
				.println("<html><body bgcolor = #0A0A2A><STYLE TYPE=\"text/css\"><!--TD{font-family: Arial; font-size: 11pt; font-weight: bold; text-align: center;	border-width: 2px;	padding: 2px;	border-style: solid;	border-color: #5858FA;	background-color: white;	-moz-border-radius: 0px 6px 0px 6px;}---></STYLE><title>Enduro Race:"
						+ txtFile + "</title><center><table>");
		while (in.ready())
			pW.println(makeRow(in, pW));
		pW.println("</table></font></body></html>");
		pW.close();
	}

	/**
	 * Parses a row to html-format from excel-format.
	 * 
	 * @param in
	 * @param out
	 * @return
	 */
	private String makeRow(BufferedReader in, PrintWriter out) {
		String inputStr = new String();
		StringBuilder output = new StringBuilder();
		output.append("<tr><td>");
		try {
			inputStr = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		output.append(inputStr.replaceAll(";", "</td><td>"));
		output.append(" </td></tr>");
		return output.toString().replaceAll("<td> </td>", "<td> - </td>");
	}
}
