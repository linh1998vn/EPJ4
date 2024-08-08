import axios from "axios"


const baseUrl = 'http://localhost:8686/api/teacher/get-all-teacher-for-classroom'

export async function getStudentList() {
    const response = await axios.get(baseUrl)
    const data = await response.data
    console.log(data)
return(data)
}

