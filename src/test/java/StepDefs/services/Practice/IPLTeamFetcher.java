package StepDefs.services.Practice;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class IPLTeamFetcher {
    public static void main(String[] args) {
        String url = "https://www.iplt20.com/points-table/men";
        try {
            // Fetch the HTML content with a user-agent to prevent blocking
            Document document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .get();

            // Locate the points table
            Element pointsTable = document.selectFirst("table");

            if (pointsTable != null) {
                Elements rows = pointsTable.select("tbody tr"); // Select only table rows
                System.out.println("IPL Teams:");

                for (Element row : rows) {
                    Elements cols = row.select("td");

                    // Ensure enough columns exist before accessing team names
                    if (cols.size() > 1) {
                        String teamName = cols.get(1).text(); // Adjust index based on actual table structure
                        System.out.println(teamName);
                    } else {
                        System.out.println("Skipping row due to insufficient columns: " + row.text());
                    }
                }
            } else {
                System.out.println("Points table not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
