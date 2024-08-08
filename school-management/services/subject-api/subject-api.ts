import axios from "axios"

const axiosClient = axios.create({
    baseURL: 'http://localhost:8686/api/',
    headers: {
        'Content-Type': 'application/json'
    }
})

const baseUrl = 'http://localhost:8686/api/subject/get-all-subject'

export async function getSubjectList() {
    const response = await axios.get(baseUrl)
    const data = await response.data
    console.log(data)
return(data)
}

const Url = 'subject/create-subject'

export const CreateSubject = (param: any) => {
    return axiosClient.post(`${Url}`, param)

}

