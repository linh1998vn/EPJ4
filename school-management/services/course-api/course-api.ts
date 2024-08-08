import axios from "axios"


const baseUrl = 'http://localhost:8686/api/course/get-all-courses'

export async function getCourseList() {
    const response = await axios.get(baseUrl)
    const data = await response.data
    console.log(data)
return(data)
}