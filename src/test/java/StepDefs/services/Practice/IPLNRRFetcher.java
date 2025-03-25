package StepDefs.services.Practice;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class IPLNRRFetcher {
    public static void main(String[] args) {
        String url = "https://www.iplt20.com/points-table/men";
        try {
            // Fetch the HTML content with user-agent to prevent blocking
            Document document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .get();

            // Select the points table
            Element pointsTable = document.selectFirst("table");

            if (pointsTable != null) {
                Elements rows = pointsTable.select("tbody tr"); // Selecting only table rows
                String topTeam = "";
                double highestNRR = Double.NEGATIVE_INFINITY;

                for (Element row : rows) {
                    Elements cols = row.select("td");

                    // Ensure there are at least 4 columns before accessing
                    if (cols.size() >= 4) {
                        String teamName = cols.get(1).text(); // Adjust index based on actual structure

                        // Extract and clean NRR value
                        String nrrText = cols.get(cols.size() - 3).text().replaceAll("[^0-9.-]", "").trim();

                        // Debug: Print extracted values
                        System.out.println("Team: " + teamName + ", Raw NRR: " + nrrText);

                        // Check if NRR value is valid before parsing
                        if (!nrrText.isEmpty()) {
                            double nrr = Double.parseDouble(nrrText);

                            if (nrr > highestNRR) {
                                highestNRR = nrr;
                                topTeam = teamName;
                            }
                        }
                    } else {
                        // Debug: Log row issue
                        System.out.println("Skipping row due to insufficient columns: " + cols.text());
                    }
                }

                System.out.println("Team with the highest NRR: " + topTeam + " (" + highestNRR + ")");
            } else {
                System.out.println("Points table not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
