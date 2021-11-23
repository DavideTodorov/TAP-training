from flask import Flask, request, redirect, url_for, render_template
import requests
import json

app = Flask(__name__, static_folder='templates/')


@app.route('/', methods=['GET', 'POST'])
def go_home():
    if request.method == 'GET':
        return render_template('home.html')

    if request.method == 'POST':
        first_currency = request.form['first_currency'].upper()
        second_currency = request.form['second_currency'].upper()


        r = requests.get(f"https://open.er-api.com/v6/latest/{first_currency}")

        try:
            currency_dict = r.json()["rates"]
        except KeyError:
            currency_rate_str = "You have entered invalid currencies!"
            return render_template('home.html', rate=currency_rate_str)

        first_currency_val = currency_dict[first_currency]
        second_currency_val = currency_dict[second_currency]

        print()

        currency_rate_str = f"For 1 {first_currency} you can buy {second_currency_val/1} {second_currency}"

        return render_template('home.html', rate=currency_rate_str)


if __name__ == '__main__':
    app.run(debug=True)
