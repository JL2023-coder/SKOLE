from flask import Flask, request, render_template
import pandas as pd
import pickle

app = Flask(__name__)

with open('model.pkl', 'rb') as file:
    model = pickle.load(file)

@app.route('/')
def home():
    return render_template('index.html')

@app.route('/predict', methods=['POST'])
def predict():
    data = [request.form.get(var) for var in ['Pclass', 'Sex', 'Age', 'SibSp', 'Parch', 'Fare']]
    
    data[1] = 1 if data[1].lower() == 'male' else 0

    input_data = pd.DataFrame([data], columns=['Pclass', 'Sex', 'Age', 'SibSp', 'Parch', 'Fare'])
    
    prediction = model.predict(input_data)[0]

    result = 'Overlevde' if prediction == 1 else 'Overlevde ikke'
    return render_template('index.html', prediction=result)

if __name__ == '__main__':
    app.run(debug=True, port=8080)
