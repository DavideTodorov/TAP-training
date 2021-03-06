import requests
from behave import Given, When, Then

# Test 1
import main


@Given("getting usd json")
def get_usd_json(context):
    new_context = context
    print(new_context)


@When("invoking the api")
def get_the_json_from_the_api(context):
    context.result_json = main.get_result()


@Then("assert there is result")
def assert_result_is_present(context):
    print(context.result_json)
    assert (len(str(context.result_json)) > 0)


# Test 2
@Given("getting invalid request")
def get_usd_json(context):
    context.api = "http://127.0.0.1:5000/?currency=asd"


@When("invoking the api with invalid currency")
def get_invalid_result(context):
    context.result_json = main.get_result()


@Then("assert there is and error")
def assert_the_error_is_expected(context):
    print(context.result_json)
    assert (context.result_json['error-type'] == 'unsupported-code')


# Test 3
@Given("getting api response for usd")
def load_static_json(context):
    context.api = "http://127.0.0.1:5000/?currency=asd"
    context.static_json = '''{
  "base_code": "USD", 
  "documentation": "https://www.exchangerate-api.com/docs/free", 
  "provider": "https://www.exchangerate-api.com", 
  "rates": {
    "AED": 3.67, 
    "AFN": 94.22, 
    "ALL": 107.71, 
    "AMD": 481.64, 
    "ANG": 1.79, 
    "AOA": 584.25, 
    "ARS": 100.66, 
    "AUD": 1.4, 
    "AWG": 1.79, 
    "AZN": 1.7, 
    "BAM": 1.73, 
    "BBD": 2, 
    "BDT": 85.73, 
    "BGN": 1.73, 
    "BHD": 0.376, 
    "BIF": 1988.2, 
    "BMD": 1, 
    "BND": 1.37, 
    "BOB": 6.88, 
    "BRL": 5.62, 
    "BSD": 1, 
    "BTN": 75.26, 
    "BWP": 11.78, 
    "BYN": 2.54, 
    "BZD": 2, 
    "CAD": 1.27, 
    "CDF": 1998.82, 
    "CHF": 0.924, 
    "CLP": 831.29, 
    "CNY": 6.39, 
    "COP": 3965.28, 
    "CRC": 635.55, 
    "CUC": 1, 
    "CUP": 25, 
    "CVE": 97.74, 
    "CZK": 22.76, 
    "DJF": 177.72, 
    "DKK": 6.61, 
    "DOP": 56.5, 
    "DZD": 138.93, 
    "EGP": 15.71, 
    "ERN": 15, 
    "ETB": 48.12, 
    "EUR": 0.886, 
    "FJD": 2.12, 
    "FKP": 0.751, 
    "FOK": 6.61, 
    "GBP": 0.751, 
    "GEL": 3.1, 
    "GGP": 0.751, 
    "GHS": 6.12, 
    "GIP": 0.751, 
    "GMD": 52.62, 
    "GNF": 9536.72, 
    "GTQ": 7.73, 
    "GYD": 209.01, 
    "HKD": 7.8, 
    "HNL": 24.09, 
    "HRK": 6.68, 
    "HTG": 98.83, 
    "HUF": 326.86, 
    "IDR": 14280.09, 
    "ILS": 3.18, 
    "IMP": 0.751, 
    "INR": 75.27, 
    "IQD": 1457.69, 
    "IRR": 41998.73, 
    "ISK": 130.18, 
    "JMD": 155.84, 
    "JOD": 0.709, 
    "JPY": 113.61, 
    "KES": 112.37, 
    "KGS": 84.75, 
    "KHR": 4061.93, 
    "KID": 1.4, 
    "KMF": 436.09, 
    "KRW": 1191.68, 
    "KWD": 0.3, 
    "KYD": 0.833, 
    "KZT": 434.6, 
    "LAK": 10794.8, 
    "LBP": 1507.5, 
    "LKR": 201.76, 
    "LRD": 142.62, 
    "LSL": 16.15, 
    "LYD": 4.61, 
    "MAD": 9.24, 
    "MDL": 17.74, 
    "MGA": 3981.83, 
    "MKD": 54.73, 
    "MMK": 1780.82, 
    "MNT": 2846.68, 
    "MOP": 8.03, 
    "MRU": 36.21, 
    "MUR": 43.43, 
    "MVR": 15.4, 
    "MWK": 815.14, 
    "MXN": 21.77, 
    "MYR": 4.23, 
    "MZN": 64, 
    "NAD": 16.15, 
    "NGN": 422.32, 
    "NIO": 35.2, 
    "NOK": 9.05, 
    "NPR": 120.42, 
    "NZD": 1.47, 
    "OMR": 0.384, 
    "PAB": 1, 
    "PEN": 4.03, 
    "PGK": 3.53, 
    "PHP": 50.39, 
    "PKR": 175.58, 
    "PLN": 4.16, 
    "PYG": 6856.91, 
    "QAR": 3.64, 
    "RON": 4.38, 
    "RSD": 104.48, 
    "RUB": 74.91, 
    "RWF": 1034.48, 
    "SAR": 3.75, 
    "SBD": 8, 
    "SCR": 13.66, 
    "SDG": 438.69, 
    "SEK": 9.1, 
    "SGD": 1.37, 
    "SHP": 0.751, 
    "SLL": 10967.22, 
    "SOS": 577.94, 
    "SRD": 21.53, 
    "SSP": 311.53, 
    "STN": 21.72, 
    "SYP": 2503.75, 
    "SZL": 16.15, 
    "THB": 33.73, 
    "TJS": 11.26, 
    "TMT": 3.5, 
    "TND": 2.89, 
    "TOP": 2.27, 
    "TRY": 12.45, 
    "TTD": 6.76, 
    "TVD": 1.4, 
    "TWD": 27.79, 
    "TZS": 2299.73, 
    "UAH": 27.14, 
    "UGX": 3562.1, 
    "USD": 1, 
    "UYU": 44.01, 
    "UZS": 10766.81, 
    "VES": 4.59, 
    "VND": 22663.33, 
    "VUV": 112.7, 
    "WST": 2.59, 
    "XAF": 581.45, 
    "XCD": 2.7, 
    "XDR": 0.714, 
    "XOF": 581.45, 
    "XPF": 105.78, 
    "YER": 250.13, 
    "ZAR": 16.15, 
    "ZMW": 17.72
  }, 
  "result": "success", 
  "terms_of_use": "https://www.exchangerate-api.com/terms", 
  "time_eol_unix": 0, 
  "time_last_update_unix": 1638230551, 
  "time_last_update_utc": "Tue, 30 Nov 2021 00:02:31 +0000", 
  "time_next_update_unix": 1638318221, 
  "time_next_update_utc": "Wed, 01 Dec 2021 00:23:41 +0000"
}'''


@When("invoking the api with usd")
def get_json_for_usd(context):
    context.result_json = main.get_result()


@Then("assert jsons are equal")
def assert_the_jsons_are_equal(context):
    assert (context.result_json == context.static_json)

# Test 4

# @Given("mocked response for usd")
# def getting_mocked_response(context):
#     context.mocked_response = "dssd"
#
#
# @When("mocking the response")
# def make_the_mocked_response(context):
#     context.result_json = ''
#
#
# # @Then("assert there is result")
# # def assert_value_is_3(context):
# #     assert
