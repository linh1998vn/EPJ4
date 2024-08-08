import axios from "axios"


const baseUrl = 'http://localhost:8686/api/student/get-all-student'

export async function getStudentList() {
    const response = await axios.get(baseUrl)
    const data = await response.data
    console.log(data)
return(data)
}