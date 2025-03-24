from flask import Flask, request, render_template, flash
import pandas as pd
import pickle
import numpy as np

app = Flask(__name__)
app.secret_key = 'your_secret_key_here'

with open('model.pkl', 'rb') as file:
    model = pickle.load(file)

@app.route('/')
def home():
    return render_template('index.html')

@app.route('/predict', methods=['POST'])
def predict():
    all_features = ['alder', 'utdanning', 'inntekt_$25-$50k', 'inntekt_>$50k', 'inntekt_under $11k', 
                'kjønn_male', 'etnisitet_black', 'etnisitet_hispanic', 'etnisitet_other', 'etnisitet_white', 
                'sykehusdød', 'blodtrykk', 'hvite_blodlegemer', 'hjertefrekvens', 'respirasjonsfrekvens', 
                'kroppstemperatur', 'lungefunksjon', 'serumalbumin', 'bilirubin', 'kreatinin', 'natrium', 
                'blod_ph', 'glukose', 'blodurea_nitrogen', 'urinmengde', 'dødsfall', 'antall_komorbiditeter', 
                'koma_score', 'adl_pasient', 'adl_stedfortreder', 'fysiologisk_score', 'apache_fysiologisk_score', 
                'overlevelsesestimat_2mnd', 'overlevelsesestimat_6mnd', 'diabetes', 'demens', 
                'lege_overlevelsesestimat_2mnd', 'lege_overlevelsesestimat_6mnd', 'dnr_dag',
                'sykdomskategori_id_A1s', 'sykdomskategori_id_BrY', 'sykdomskategori_id_ChE', 
                'sykdomskategori_id_DWw', 'sykdomskategori_ARF/MOSF', 'sykdomskategori_COPD/CHF/Cirrhosis', 
                'sykdomskategori_Cancer', 'sykdomskategori_Coma', 'sykdom_underkategori_ARF/MOSF w/Sepsis', 
                'sykdom_underkategori_CHF', 'sykdom_underkategori_COPD', 'sykdom_underkategori_Cirrhosis', 
                'sykdom_underkategori_Colon Cancer', 'sykdom_underkategori_Coma', 
                'sykdom_underkategori_Lung Cancer', 'sykdom_underkategori_MOSF w/Malig', 'kreft_metastatic', 
                'kreft_no', 'kreft_yes', 'dnr_status_dnr før innleggelse', 'dnr_status_dnr ved innleggelse']

    input_data = {}
    for feature in all_features:
        value = request.form.get(feature, '')
        if value == '':
            input_data[feature] = np.nan
        else:
            try:
                if feature in ['sykdomskategori_id_A1s', 'sykdomskategori_id_BrY', 'sykdomskategori_id_ChE', 
                               'sykdomskategori_id_DWw', 'sykdomskategori_ARF/MOSF', 'sykdomskategori_COPD/CHF/Cirrhosis', 
                               'sykdomskategori_Cancer', 'sykdomskategori_Coma', 'sykdom_underkategori_ARF/MOSF w/Sepsis', 
                               'sykdom_underkategori_CHF', 'sykdom_underkategori_COPD', 'sykdom_underkategori_Cirrhosis', 
                               'sykdom_underkategori_Colon Cancer', 'sykdom_underkategori_Coma', 
                               'sykdom_underkategori_Lung Cancer', 'sykdom_underkategori_MOSF w/Malig', 'kreft_metastatic', 
                               'kreft_no', 'kreft_yes', 'dnr_status_dnr før innleggelse', 'dnr_status_dnr ved innleggelse']:
                    input_data[feature] = 1 if value == 'on' else 0
                else:
                    input_data[feature] = float(value) if '.' in value else int(value)
            except ValueError:
                input_data[feature] = np.nan

    df = pd.DataFrame([input_data])
    
    try:
        prediction = model.predict(df)[0]
        if prediction < 0:
            flash('Warning: Negative prediction value. Result may be unreliable.', 'warning')
        return render_template('index.html', prediction=f'Forventet sykehusopphold: {prediction:.2f} dager')
    except Exception as e:
        flash(f'An error occurred during prediction: {str(e)}. Please check your inputs and try again.', 'error')
        return render_template('index.html')

if __name__ == '__main__':
    app.run(debug=True, port=8080)