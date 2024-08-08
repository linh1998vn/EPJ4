import axios from "axios"


const baseUrl = 'http://localhost:8686/api/classroom/get-all-classroom'

export async function getClassroomList() {
    const response = await axios.get(baseUrl)
    const data = await response.data
    console.log(data)
return(data)
}