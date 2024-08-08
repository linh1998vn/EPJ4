"use client"

import { Button } from '@/components/ui/button'
import { Calendar } from '@/components/ui/calendar'
import { Input } from '@/components/ui/input'
import { AlignEndHorizontal, BookOpen, GraduationCap, Phone, Send } from 'lucide-react'
import React from 'react'

export default function StudentDashBoard() {
  const [date, setDate] = React.useState<Date | undefined>(new Date())

  return (
    <div className="grid grid-cols-8 gap-6">
        <div className='col-span-8'>
          <h1 className='text-3xl font-bold'>My Classes</h1>
        </div>
        <div className='col-span-4 grid grid-rows-4 grid-flow-col gap-4'>
          <div className='grid grid-cols-2 gap-4 bg-[#12121E] text-white rounded-2xl p-4'>
            <div>
              <h4 className='text-lg font-semibold'>ReactJs</h4>
              <p className='text-sm'>Diliver web apps with confidence</p>
            </div>
          </div>
          <div className='grid grid-cols-2 gap-4 bg-[#FFEFE2] rounded-2xl p-4'>
            <div>
              <h4 className='text-lg font-semibold'>Angular</h4>
              <p className='text-sm'>Diliver web apps with confidence</p>
            </div>
          </div>
          <div className='grid grid-cols-2 gap-4 bg-[#FCF3FD] rounded-2xl p-4'>
            <div>
              <h4 className='text-lg font-semibold'>Python</h4>
              <p className='text-sm'>Diliver web apps with confidence</p>
            </div>
          </div>
          <div className='grid grid-cols-2 gap-4 bg-[#F7FAE9] rounded-2xl p-4'>
            <div>
              <h4 className='text-lg font-semibold'>NodeJs</h4>
              <p className='text-sm'>Diliver web apps with confidence</p>
            </div>
          </div>
        </div>
        <div className='col-span-2'>
          <Input type="search" placeholder="Search" />
          <Calendar
            mode="single"
            selected={date}
            onSelect={setDate}
            className="rounded-md border mt-5"
          />
        </div>
        <div className='col-span-2'>
          <div className='bg-gray-100 rounded-2xl p-2 grid justify-items-center h-full'>
            <div className='bg-[#12121E] rounded-2xl w-[125px] h-[125px] mt-5'></div>
            <h1 className="text-2xl font-semibold items-center flex">
              Tran Phuong Nam
            </h1>
            <p className="text-base font-medium">T2104E</p>
            <div className='grid grid-cols-3 gap-4'>
              <AlignEndHorizontal />
              <Phone />
              <Send />
            </div>
            <Button>
              <GraduationCap className='mr-2' /> Student Information
            </Button>
          </div>
        </div>
        <div className='col-span-4 grid grid-rows-4 grid-flow-col gap-4 mt-4'>
          <div>
            <h1 className="text-3xl font-bold">Your Homework</h1>
          </div>
          <div className='grid grid-cols-8 gap-4'>
            <div className='col-span-1 rounded-full bg-[#12121E] text-white p-4 w-[56px]'>
              <BookOpen />
            </div>
            <div className='col-span-4 grid content-center'>
              <h1 className="text-medium font-semibold">
                Create Dashboard
              </h1>
              <p className="text-sm max-w-xs">3 Aug 2022</p>
            </div>
            <div className='col-span-1 grid content-center'>
              <h1 className="text-medium font-semibold">80/100</h1>
              <p className="text-sm max-w-xs">Good</p>
            </div>
            <div className='col-span-2 grid content-center justify-end'>
              <span className="text-center inline-flex items-center bg-green-100 text-green-800 text-sm font-medium px-2.5 py-0.5 rounded-full">
                Completed
              </span>
            </div>
          </div>
          <div className='grid grid-cols-8 gap-4'>
            <div className='col-span-1 rounded-full bg-[#12121E] text-white p-4 w-[56px]'>
              <BookOpen />
            </div>
            <div className='col-span-4 grid content-center'>
              <h1 className="text-medium font-semibold">
                Shopping Cart
              </h1>
              <p className="text-sm max-w-xs">10 Aug 2022</p>
            </div>
            <div className='col-span-1 grid content-center'>
              <h1 className="text-medium font-semibold">--/100</h1>
              <p className="text-sm max-w-xs">To do</p>
            </div>
            <div className='col-span-2 grid content-center justify-end'>
              <span className="text-center inline-flex items-center bg-orange-100 text-orange-800 text-sm font-medium px-2.5 py-0.5 rounded-full">
                Pending
              </span>
            </div>
          </div>
          <div className='grid grid-cols-8 gap-4'>
            <div className='col-span-1 rounded-full bg-[#12121E] text-white p-4 w-[56px]'>
              <BookOpen />
            </div>
            <div className='col-span-4 grid content-center'>
              <h1 className="text-medium font-semibold">
                Ecommerce Website
              </h1>
              <p className="text-sm max-w-xs">1 Aug 2022</p>
            </div>
            <div className='col-span-1 grid content-center'>
              <h1 className="text-medium font-semibold">20/100</h1>
              <p className="text-sm max-w-xs">Bad</p>
            </div>
            <div className='col-span-2 grid content-center justify-end'>
              <span className="text-center inline-flex items-center bg-red-100 text-red-800 text-sm font-medium px-2.5 py-0.5 rounded-full">
                Uncompleted
              </span>
            </div>
          </div>
        </div>
        <div className='col-span-4'>
        </div>
    </div>
  )
}
