#Imports library to handle excel sheets. Give it an alias for less typing.
import openpyxl as op

#Loads a workbook
workbook = op.load_workbook(filename='test.xlsx')

sheet = workbook.active

for i in range(1, sheet.max_row + 1):
    sheet[f"C{i}"] = f"=B{i}-A{i}"

workbook.save('test.xlsx')