# https://open.er-api.com/v6/latest/{first_currency}
#

from flask import Flask, request, render_template, jsonify
import requests
import json
from cachetools import cached, TTLCache


# You should pass currency code in capital letters as query param in order to work
def get_result():
    a = "USD"

    currency_json = requests.get(f"https://open.er-api.com/v6/latest/{a}").json()

    return currency_json
