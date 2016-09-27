import re

data_file = open("ssu_course_list_fall_2014_first_draft.tsv")
lines = list(data_file)
#print(str(lines))



def findClassesByTeacher(name):
    tmpList = []
    pat = re.compile("Ted")
    for c in lines:
        #tmpList = pat.findall(c)

        tmpList.append(pat.match(c, 0))
    return tmpList

def lineToClass(line):
    pass

print(findClassesByTeacher("ted"))
