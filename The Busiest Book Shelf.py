# Enter your code here. Read input from STDIN. Print output to STDOUT

from collections import Counter
import sys

data = list(map(int, sys.stdin.read().split()))
n = data[0]
books = data[1:n + 1]

freq = Counter(books)
max_freq = max(freq.values())

answer = min(book_id for book_id, count in freq.items() if count == max_freq)

print(answer)
