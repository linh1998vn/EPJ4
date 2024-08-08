import Image from 'next/image'
import TeacherDashBoard from './(teacher)/dashboard/page'
import StudentDashBoard from './(student)/dashboard/page'
import AdminDashBoard from './(admin)/dashboard/page'

export default function Home() {
  return (
    <>
      <AdminDashBoard />
    </>
  )
}
