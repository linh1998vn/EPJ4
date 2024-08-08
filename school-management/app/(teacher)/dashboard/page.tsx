"use client"

import { ArrowRight, ChevronRight, Code2, Codesandbox, Database, Layout } from 'lucide-react'
import { Calendar } from '@/components/ui/calendar'
import React from 'react'

export default function TeacherDashBoard() {
  const [date, setDate] = React.useState<Date | undefined>(new Date())

  return (
    <div className='grid grid-cols-8 gap-6'>
      <div className='col-span-8'>
        <h1 className='text-3xl font-bold'>My Classes</h1>
      </div>
      <div className='col-span-6 grid grid-rows-2 gap-4'>
        <div className='grid grid-cols-3 gap-4 w-full'>
          <div className='bg-[#FFEFE2] rounded-xl p-4'>
            <div className='bg-orange-500 rounded-xl h-[150px]'></div>
            <p className="text-base font-medium mt-2">T2104E</p>
            <p className='text-xs font-light'>12 lessions</p>
            <div className='grid grid-cols-2 gap-4 mt-24'>
              <div className="flex -space-x-4 col-start-1 col-end-3">
                <img className="w-10 h-10 border-2 border-white rounded-full dark:border-gray-800" src="/docs/images/people/profile-picture-5.jpg" alt="" />
                <img className="w-10 h-10 border-2 border-white rounded-full dark:border-gray-800" src="/docs/images/people/profile-picture-2.jpg" alt="" />
                <img className="w-10 h-10 border-2 border-white rounded-full dark:border-gray-800" src="/docs/images/people/profile-picture-3.jpg" alt="" />
                <a className="flex items-center justify-center w-10 h-10 text-xs font-medium text-white bg-gray-700 border-2 border-white rounded-full hover:bg-gray-600 dark:border-gray-800" href="#">+25</a>
              </div>
              <div className='bg-orange-300 p-2 rounded-xl w-[40px] col-end-7'>
                <ChevronRight />
              </div>
            </div>
          </div>
          <div className='bg-[#FCF3FD] rounded-xl p-4'>
            <div className='bg-purple-500 rounded-xl h-[150px]'></div>
            <p className="text-base font-medium mt-2">T1103A</p>
            <p className='text-xs font-light'>24 lessions</p>
            <div className='grid grid-cols-2 gap-4 mt-24'>
              <div className="flex -space-x-4 col-start-1 col-end-3">
                <img className="w-10 h-10 border-2 border-white rounded-full dark:border-gray-800" src="/docs/images/people/profile-picture-5.jpg" alt="" />
                <img className="w-10 h-10 border-2 border-white rounded-full dark:border-gray-800" src="/docs/images/people/profile-picture-2.jpg" alt="" />
                <img className="w-10 h-10 border-2 border-white rounded-full dark:border-gray-800" src="/docs/images/people/profile-picture-3.jpg" alt="" />
                <a className="flex items-center justify-center w-10 h-10 text-xs font-medium text-white bg-gray-700 border-2 border-white rounded-full hover:bg-gray-600 dark:border-gray-800" href="#">+24</a>
              </div>
              <div className='bg-purple-300 p-2 rounded-xl w-[40px] col-end-7'>
                <ChevronRight />
              </div>
            </div>
          </div>
          <div className='bg-[#F7FAE9] rounded-xl p-4'>
            <div className='bg-green-500 rounded-xl h-[150px]'></div>
            <p className="text-base font-medium mt-2">T3426W</p>
            <p className='text-xs font-light'>22 lessions</p>
            <div className='grid grid-cols-2 gap-4 mt-24'>
              <div className="flex -space-x-4 col-start-1 col-end-3">
                <img className="w-10 h-10 border-2 border-white rounded-full dark:border-gray-800" src="/docs/images/people/profile-picture-5.jpg" alt="" />
                <img className="w-10 h-10 border-2 border-white rounded-full dark:border-gray-800" src="/docs/images/people/profile-picture-2.jpg" alt="" />
                <img className="w-10 h-10 border-2 border-white rounded-full dark:border-gray-800" src="/docs/images/people/profile-picture-3.jpg" alt="" />
                <a className="flex items-center justify-center w-10 h-10 text-xs font-medium text-white bg-gray-700 border-2 border-white rounded-full hover:bg-gray-600 dark:border-gray-800" href="#">+19</a>
              </div>
              <div className='bg-green-300 p-2 rounded-xl w-[40px] col-end-7'>
                <ChevronRight />
              </div>
            </div>
          </div>
        </div>
        <div className='grid grid-cols-6 gap-4 mt-4'>
          <div className='col-span-6'>
            <h4 className="text-xl font-semibold">Your Courses</h4>
          </div>
          <div className='grid grid-cols-12 content-center col-span-6'>
            <div className='col-span-5 grid content-center'>
              <p className='font-medium text-sm text-gray-500'>Course name</p>
            </div>
            <div className='col-span-2 grid content-center'>
              <p className='font-medium text-sm text-gray-500'>Start date</p>
            </div>
            <div className='col-span-2 grid content-center'>
              <p className='font-medium text-sm text-gray-500'>Room</p>
            </div>
            <div className='col-span-1 grid content-center'>
              <p className='font-medium text-sm text-gray-500'>Class</p>
            </div>
            <div className='col-span-2 grid content-center justify-end'>
              <p className='font-medium text-sm text-gray-500'>Level</p>
            </div>
          </div>
          <div className='grid grid-cols-12 content-center col-span-6'>
            <div className='col-span-1 rounded-xl bg-[#12121E] text-white p-4 w-[55px] h-[55px]'>
              <Layout />
            </div>
            <div className='col-span-4 grid content-center'>
              <h1 className="text-medium font-semibold">
                Web Design
              </h1>
              <p className="text-sm max-w-xs">10 lessons</p>
            </div>
            <div className='col-span-2 grid content-center'>
              <h1 className="text-medium">May 12</h1>
            </div>
            <div className='col-span-2 grid content-center'>
              <h1 className="text-medium">1.2A</h1>
            </div>
            <div className='col-span-1 grid content-center'>
              <h1 className="text-medium">T2104E</h1>
            </div>
            <div className='col-span-2 grid content-center justify-end'>
              <span className="text-center inline-flex items-center bg-green-100 text-green-800 text-sm font-medium px-2.5 py-0.5 rounded-full">
                Elementary
              </span>
            </div>
          </div>
          <div className='grid grid-cols-12 content-center col-span-6'>
            <div className='col-span-1 rounded-xl bg-[#12121E] text-white p-4 w-[55px] h-[55px]'>
              <Database />
            </div>
            <div className='col-span-4 grid content-center'>
              <h1 className="text-medium font-semibold">
                Data with Python
              </h1>
              <p className="text-sm max-w-xs">8 lessons</p>
            </div>
            <div className='col-span-2 grid content-center'>
              <h1 className="text-medium">May 14</h1>
            </div>
            <div className='col-span-2 grid content-center'>
              <h1 className="text-medium">2.2A</h1>
            </div>
            <div className='col-span-1 grid content-center'>
              <h1 className="text-medium">T2103A</h1>
            </div>
            <div className='col-span-2 grid content-center justify-end'>
              <span className="text-center inline-flex items-center bg-red-100 text-red-800 text-sm font-medium px-2.5 py-0.5 rounded-full">
                Intermediate
              </span>
            </div>
          </div>
          <div className='grid grid-cols-12 content-center col-span-6'>
            <div className='col-span-1 rounded-xl bg-[#12121E] text-white p-4 w-[55px] h-[55px]'>
              <Code2 />
            </div>
            <div className='col-span-4 grid content-center'>
              <h1 className="text-medium font-semibold">
                Html & Css Basic
              </h1>
              <p className="text-sm max-w-xs">12 lessons</p>
            </div>
            <div className='col-span-2 grid content-center'>
              <h1 className="text-medium">Aug 12</h1>
            </div>
            <div className='col-span-2 grid content-center'>
              <h1 className="text-medium">1.5B</h1>
            </div>
            <div className='col-span-1 grid content-center'>
              <h1 className="text-medium">T2102R</h1>
            </div>
            <div className='col-span-2 grid content-center justify-end'>
              <span className="text-center inline-flex items-center bg-green-100 text-green-800 text-sm font-medium px-2.5 py-0.5 rounded-full">
                Elementary
              </span>
            </div>
          </div>
          <div className='grid grid-cols-12 content-center col-span-6'>
            <div className='col-span-1 rounded-xl bg-[#12121E] text-white p-4 w-[55px] h-[55px]'>
              <Codesandbox />
            </div>
            <div className='col-span-4 grid content-center'>
              <h1 className="text-medium font-semibold">
                Java App
              </h1>
              <p className="text-sm max-w-xs">15 lessons</p>
            </div>
            <div className='col-span-2 grid content-center'>
              <h1 className="text-medium">Jul 12</h1>
            </div>
            <div className='col-span-2 grid content-center'>
              <h1 className="text-medium">2.2A</h1>
            </div>
            <div className='col-span-1 grid content-center'>
              <h1 className="text-medium">T2201Q</h1>
            </div>
            <div className='col-span-2 grid content-center justify-end'>
              <span className="text-center inline-flex items-center bg-red-100 text-red-800 text-sm font-medium px-2.5 py-0.5 rounded-full">
                Intermediate
              </span>
            </div>
          </div>
        </div>
      </div>
      <div className='col-span-2 grid'>
        <div className='p-4 justify-items-center grid h-[125px]'>
          <div className='bg-[#12121E] rounded-xl h-[115px] w-[115px]'></div>
          <p className="text-base font-medium mt-2">Trần Phương Nam</p>
          <p className='text-sm font-light'>Teacher</p>
        </div>
        <div className='mt-12'>
          <Calendar
            mode="single"
            selected={date}
            onSelect={setDate}
            className="rounded-md border mt-5"
          />
        </div>
        <div className='grid grid-cols-4 gap-2 mt-2'>
          <div className='col-span-4'>
            <h4 className="text-xl font-semibold">Homework Progress</h4>
          </div>
          <div className='col-span-4 bg-[#12121E] rounded-xl p-2 grid grid-cols-12 content-center'>
            <div className='col-span-11 text-white grid content-center'>
              <h1 className="text-base font-medium">
                Styling With Css
              </h1>
              <p className="text-xs">T2104E</p>
            </div>
            <div className='col-span-1 text-white grid content-center'>
              <ArrowRight />
            </div>
          </div>
          <div className='col-span-4 bg-[#12121E] rounded-xl p-2 grid grid-cols-12 content-center'>
            <div className='col-span-11 text-white grid content-center'>
              <h1 className="text-base font-medium">
                Create Database
              </h1>
              <p className="text-xs">T2346X</p>
            </div>
            <div className='col-span-1 text-white grid content-center'>
              <ArrowRight />
            </div>
          </div>
          <div className='col-span-4 bg-[#12121E] rounded-xl p-2 grid grid-cols-12 content-center'>
            <div className='col-span-11 text-white grid content-center '>
              <h1 className="text-base font-medium">
                Rest API
              </h1>
              <p className="text-xs">T1102A</p>
            </div>
            <div className='col-span-1 text-white grid content-center'>
              <ArrowRight />
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
