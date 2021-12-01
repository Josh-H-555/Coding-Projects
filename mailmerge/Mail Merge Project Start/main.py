with open("Input/Names/invited_names.txt") as name_file:
    for names in name_file.readlines():
        with open("Input/Letters/starting_letter.txt") as letter_file:
            new_letter = letter_file.read().replace("[name]", names.strip("\n"))
            names = names.strip("\n")
            with open(f"Output/ReadyToSend/{names}_letter.txt", mode='w') as named_letter:
                named_letter.write(new_letter)
