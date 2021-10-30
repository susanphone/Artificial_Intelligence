
def stripBIF(file):
    bif = file.open(file, "r")
    for line in bif:
        stripped = line.strip("]}{[")
        print(line)
    file.close()
    return(stripped)

def main(file):
    strip = stripBIF(file)
    print(strip)


main("/Artificial_Intelligence/Projects/Project3/test.bif")
