input = open("input.txt","r")

score = 0

for line in input.readlines():
    print(line)
    card = line.split(":")
    cardnums = card[1].split("|")
    winners = cardnums[0].strip().split(" ")
    numbers = cardnums[1].strip().split(" ")

    for num in winners:
        num = num.strip()

    wins = 0
    for num in numbers:
        num = num.strip()
        if(num in winners) and num:
            wins += 1
    if wins > 0:
        score += 2**(wins-1)

print(score)
