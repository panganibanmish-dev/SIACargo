import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.testobject.ObjectRepository as OR
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testdata.CSVData as CSVData

// Load test data from CSV file
CSVData data = TestDataFactory.findTestData('data')

// Loop through each row in the test data
for (def i = 1; i <= data.getRowNumbers(); i++) {
    def ORIGIN = data.getValue(1, 2)

    def DESTINATION = data.getValue(2, 2)

    def DEPARTURE_DATE = data.getValue(3, 1)

    // Open the browser
    WebUI.openBrowser('')

    // Navigate to the specified URL
    WebUI.navigateToUrl('http://www.siacargo.com')

    WebUI.maximizeWindow()

    WebUI.waitForPageLoad(5)

    WebUI.click(findTestObject('Page_Siacargo/accept_cookies'))

    // Click on 'E-SERVICES' and then 'Flight Schedule'
    WebUI.click(findTestObject('Page_Siacargo/E-SERVICES'))

    WebUI.click(findTestObject('Page_Siacargo/btn_flight_schedule'))

    // Fill in ORIGIN, DESTINATION, and DEPARTURE DATE
    WebUI.setText(findTestObject('Page_Siacargo/input_origin'), ORIGIN)

    WebUI.setText(findTestObject('Page_Siacargo/input_destination'), DESTINATION)

    WebUI.setText(findTestObject('Page_Siacargo/input_date_departure'), DEPARTURE_DATE)

    WebUI.click(findTestObject('Page_Siacargo/select_date_departure'))

    // Click on SEARCH button
    WebUI.click(findTestObject('Page_Siacargo/button_search'))

    WebUI.click(findTestObject('Page_Siacargo/next_btn'))

    WebUI.waitForPageLoad(5)

    // Verify that flight number 'SQ0211' for '04 Mar Mon' is displayed in the search results
    WebUI.verifyElementVisible(findTestObject('Page_Siacargo/flight_number'))

    // Click on 'Reset / New Search'
    WebUI.click(findTestObject('Page_Siacargo/reset_search'))

    WebUI.setText(findTestObject('Page_Siacargo/input_origin'), ORIGIN)

    WebUI.setText(findTestObject('Page_Siacargo/input_destination'), DESTINATION)

    // Click on SEARCH button without entering the DEPARTURE DATE
    WebUI.click(findTestObject('Page_Siacargo/button_search'))

    // Verify that the error prompt is visible
    WebUI.verifyElementVisible(findTestObject('Page_Siacargo/message_error'))

    // Close the browser
    WebUI.closeBrowser()
}

