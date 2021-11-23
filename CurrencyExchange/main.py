from flask import Flask, request, render_template
import requests
import json
from cachetools import cached, TTLCache

app = Flask(__name__, static_folder='templates/')

# Store data in cache for 3 hours
cache = TTLCache(maxsize=100, ttl=10800)


def get_joke():
    try:
        joke_response = requests.get("https://v2.jokeapi.dev/joke/Any")
        random_joke_setup = joke_response.json()["setup"]
        random_joke_delivery = joke_response.json()["delivery"]
        random_joke_text = random_joke_setup + " " + random_joke_delivery
        return random_joke_text
    except KeyError:
        return "Sorry, the API couldn't load a joke :("


@cached(cache)
@app.route('/', methods=['GET', 'POST'])
def go_home():
    if request.method == 'GET':
        random_joke_text = get_joke()

        return render_template('home.html', joke=random_joke_text)

    if request.method == 'POST':
        first_currency = request.form['first_currency'].upper()
        second_currency = request.form['second_currency'].upper()

        currencies_response = requests.get(f"https://open.er-api.com/v6/latest/{first_currency}")

        try:
            currency_dict = currencies_response.json()["rates"]
        except KeyError:
            currency_rate_str = f"'{first_currency}' is not a invalid currency!"
            random_joke_text = get_joke()
            return render_template('home.html', rate=currency_rate_str, joke=random_joke_text)
        except json.decoder.JSONDecodeError:
            currency_rate_str = f"'{first_currency}' is not a invalid currency!"
            random_joke_text = get_joke()
            return render_template('home.html', rate=currency_rate_str, joke=random_joke_text)

        first_currency_val = currency_dict[first_currency]

        try:
            second_currency_val = currency_dict[second_currency]
        except KeyError:
            currency_rate_str = f"'{second_currency}' is not a invalid currency!"
            random_joke_text = get_joke()
            return render_template('home.html', rate=currency_rate_str, joke=random_joke_text)

        currency_rate_str = f"For 1 {first_currency} you can buy {second_currency_val / 1} {second_currency}"

        random_joke_text = get_joke()

        return render_template('home.html', rate=currency_rate_str, joke=random_joke_text)



if __name__ == '__main__':
    app.run(debug=True)
