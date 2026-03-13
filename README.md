# ANBIMA Extractor

A Java-based web scraping application designed to extract investment fund data from the official ANBIMA Data portal. The tool automates browser navigation to collect comprehensive fund details and exports the gathered information into an Excel spreadsheet.

## Features

* Automated Data Extraction: Scrapes multiple pages of investment fund data from ANBIMA.
* Headless Browser Execution: Uses Selenium WebDriver with Chrome in headless mode for efficiency.
* Progress Tracking: Real-time visual feedback in the console using a professional ASCII progress bar.
* Dynamic Driver Management: Automatically handles the `chromedriver.exe` setup from resources.
* Excel Export: Saves all extracted data into a structured Excel file.

## Extracted Data Points

The application collects the following fields for each fund:
* Fund Name and Link
* CNPJ
* ANBIMA Classification
* Fund Manager (Gestor)
* Minimum Initial Investment
* Net Equity (Patrimônio Líquido)
* Administrator
* Investor Characteristics
* Maximum Administration Fee
* Profitability

## Prerequisites

* Java JDK 8 or higher.
* Google Chrome installed.
* Dependencies (Maven/Gradle):
    * Selenium WebDriver
    * Apache Commons IO
    * tongfei-progressbar
    * (Custom) ExcelExport and DataModel classes

## How It Works

1. Initialization: The app sets up the ChromeDriver and configures Chrome options (headless mode, window size).
2. Navigation: It accesses the ANBIMA funds list.
3. Pagination: The script identifies the total number of pages and iterates through them.
4. Scraping: For every page, it loops through the fund items (up to 100 per page) and extracts detailed attributes using XPath.
5. Storage: Data is stored in a list of `DataModel` objects.
6. Export: Once the scraping is complete, the `ExcelExport` utility is triggered to generate the final report.

## Usage

1. Ensure all dependencies are correctly configured in your project.
2. Run the `Main.java` class.
3. Monitor the progress via the console.
4. Find the generated Excel file in the project directory upon completion.

---

*Note: This tool is intended for educational purposes and data analysis. Please ensure compliance with ANBIMA's terms of service regarding automated data collection.*
