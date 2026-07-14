import json

rows = json.load(open('backend/src/main/resources/countries.json'))

def q(v):
    return "'" + str(v).replace("'", "''") + "'"

values = ',\n'.join(
    '  (' + ', '.join(q(r[k]) for k in ('name', 'code', 'capital', 'region', 'population', 'currency')) + ')'
    for r in rows)

with open('backend/src/main/resources/db/migration/V2__seed_country.sql', 'w') as f:
    f.write('INSERT INTO country (country, code, capital, region, population, currency) VALUES\n' + values + ';\n')
