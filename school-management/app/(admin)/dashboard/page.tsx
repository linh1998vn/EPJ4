"use client"

import { AlarmClock, Component, GraduationCap, MapPin, Microscope, Shapes } from 'lucide-react'
import { Calendar } from '@/components/ui/calendar'
import React from 'react'

import {
    Select,
    SelectContent,
    SelectGroup,
    SelectItem,
    SelectLabel,
    SelectTrigger,
    SelectValue,
} from "@/components/ui/select"
import { ScrollArea } from '@/components/ui/scroll-area'
import { Separator } from '@/components/ui/separator'

  export default function AdminDashBoard() {
    const [date, setDate] = React.useState<Date | undefined>(new Date())

  return (
    <div className='grid grid-cols-8 gap-6'>
      <div className='col-span-8'>
        <h1 className='text-3xl font-bold'>Information</h1>
      </div>
      <div className='col-span-6'>
        <div className='grid grid-cols-12 gap-4'>
            <div className='col-span-5 grid grid-cols-2 gap-4'>
                <div className='bg-[#FFEFE2] p-4 rounded-xl h-[155px]'>
                    <div className='bg-orange-500 rounded-xl w-12 h-12 grid content-center justify-center'>
                        <Microscope />
                    </div>
                    <div className='mt-6'>
                        <div className='flex'>
                            <h4 className='font-semibold text-lg'>30</h4>
                            <p className='text-xs font-semibold ml-2 text-green-500'>+ 0.5%</p>
                        </div>
                        <p className='text-sm mt-1'>Total Teachers</p>
                    </div>
                </div>
                <div className='bg-[#FCF3FD] p-4 rounded-xl h-[155px]'>
                    <div className='bg-purple-500 rounded-xl w-12 h-12 grid content-center justify-center'>
                        <GraduationCap />
                    </div>
                    <div className='mt-6'>
                        <div className='flex'>
                            <h4 className='font-semibold text-lg'>150</h4>
                            <p className='text-xs font-semibold ml-2 text-red-500'>- 5%</p>
                        </div>
                        <p className='text-sm mt-1'>Total Students</p>
                    </div>
                </div>
                <div className='bg-[#F7FAE9] p-4 rounded-xl h-[155px]'>
                    <div className='bg-green-500 rounded-xl w-12 h-12 grid content-center justify-center'>
                        <Component />
                    </div>
                    <div className='mt-6'>
                        <div className='flex'>
                            <h4 className='font-semibold text-lg'>10</h4>
                            <p className='text-xs font-semibold ml-2 text-red-500'>- 5%</p>
                        </div>
                        <p className='text-sm mt-1'>Total Courses</p>
                    </div>
                </div>
                <div className='bg-sky-200 p-4 rounded-xl h-[155px]'>
                    <div className='bg-blue-500 rounded-xl w-12 h-12 grid content-center justify-center'>
                        <Shapes />
                    </div>
                    <div className='mt-6'>
                        <div className='flex'>
                            <h4 className='font-semibold text-lg'>30</h4>
                            <p className='text-xs font-semibold ml-2 text-green-500'>+ 15%</p>
                        </div>
                        <p className='text-sm mt-1'>Total Classes</p>
                    </div>
                </div>
            </div>
            <div className="row-span-7">02</div>
        </div>
        <div className='grid grid-cols-6 gap-6 mt-8'>
            <div className='col-span-4'>
                <h4 className="text-xl font-semibold">Classes</h4>
            </div>
            <div className='col-span-2 justify-self-end'>
                <Select>
                    <SelectTrigger className="w-[180px]">
                        <SelectValue placeholder="Select Courses" />
                    </SelectTrigger>
                    <SelectContent>
                        <SelectGroup>
                            <SelectLabel>Courses</SelectLabel>
                            <SelectItem value="apple">Apple</SelectItem>
                            <SelectItem value="banana">Banana</SelectItem>
                            <SelectItem value="blueberry">Blueberry</SelectItem>
                            <SelectItem value="grapes">Grapes</SelectItem>
                            <SelectItem value="pineapple">Pineapple</SelectItem>
                        </SelectGroup>
                    </SelectContent>
                </Select>
            </div>   
          <div className='grid grid-cols-12 content-center col-span-6'>
            <div className='col-span-2 grid content-center'>
              <p className='font-medium text-sm text-gray-500'>Class name</p>
            </div>
            <div className='col-span-2 grid content-center'>
              <p className='font-medium text-sm text-gray-500'>Start date</p>
            </div>
            <div className='col-span-2 grid content-center'>
              <p className='font-medium text-sm text-gray-500'>End date</p>
            </div>
            <div className='col-span-2 grid content-center'>
              <p className='font-medium text-sm text-gray-500'>Teacher</p>
            </div>
            <div className='col-span-2 grid justify-center'>
              <p className='font-medium text-sm text-gray-500'>Students</p>
            </div>
            <div className='col-span-2 grid content-center justify-end'>
              <p className='font-medium text-sm text-gray-500'>Semester</p>
            </div>
          </div>
          <div className='grid grid-cols-12 content-center col-span-6'>
            <div className='col-span-2 grid content-center'>
              <h1 className="text-medium font-semibold">
                T2104E
              </h1>
              <p className="text-sm max-w-xs">4 Semesters</p>
            </div>
            <div className='col-span-2 grid content-center'>
              <h1 className="text-medium">May 12</h1>
            </div>
            <div className='col-span-2 grid content-center'>
              <h1 className="text-medium">May 12</h1>
            </div>
            <div className='col-span-2 grid content-center'>
                <h1 className="text-medium font-semibold">
                    Trần Phương Nam
                </h1>
                <p className="text-sm max-w-xs">M12445A</p>
            </div>
            <div className='col-span-2 grid justify-center'>
              <h1 className="text-medium mr-3">25/30</h1>
            </div>
            <div className='col-span-2 grid content-center justify-end'>
              <span className="text-center inline-flex items-center bg-green-100 text-green-800 text-sm font-medium px-2.5 py-0.5 rounded-full">
                First Semester
              </span>
            </div>
          </div>
          <div className='grid grid-cols-12 content-center col-span-6'>
            <div className='col-span-2 grid content-center'>
              <h1 className="text-medium font-semibold">
                T2103A
              </h1>
              <p className="text-sm max-w-xs">4 Semesters</p>
            </div>
            <div className='col-span-2 grid content-center'>
              <h1 className="text-medium">Aug 12</h1>
            </div>
            <div className='col-span-2 grid content-center'>
              <h1 className="text-medium">Aug 12</h1>
            </div>
            <div className='col-span-2 grid content-center'>
                <h1 className="text-medium font-semibold">
                    Trần Phương Nam
                </h1>
                <p className="text-sm max-w-xs">M12445A</p>
            </div>
            <div className='col-span-2 grid justify-center'>
              <h1 className="text-medium mr-3">25/30</h1>
            </div>
            <div className='col-span-2 grid content-center justify-end'>
              <span className="text-center inline-flex items-center bg-blue-100 text-blue-800 text-sm font-medium px-2.5 py-0.5 rounded-full">
                Second Semester
              </span>
            </div>
          </div>
          <div className='grid grid-cols-12 content-center col-span-6'>
            <div className='col-span-2 grid content-center'>
              <h1 className="text-medium font-semibold">
                T2102C
              </h1>
              <p className="text-sm max-w-xs">4 Semesters</p>
            </div>
            <div className='col-span-2 grid content-center'>
              <h1 className="text-medium">Jul 12</h1>
            </div>
            <div className='col-span-2 grid content-center'>
              <h1 className="text-medium">Jul 12</h1>
            </div>
            <div className='col-span-2 grid content-center'>
                <h1 className="text-medium font-semibold">
                    Trần Phương Nam
                </h1>
                <p className="text-sm max-w-xs">M12445A</p>
            </div>
            <div className='col-span-2 grid justify-center'>
              <h1 className="text-medium mr-3">30/30</h1>
            </div>
            <div className='col-span-2 grid content-center justify-end'>
              <span className="text-center inline-flex items-center bg-orange-100 text-orange-800 text-sm font-medium px-2.5 py-0.5 rounded-full">
                Third Semester
              </span>
            </div>
          </div>
          <div className='grid grid-cols-12 content-center col-span-6'>
            <div className='col-span-2 grid content-center'>
              <h1 className="text-medium font-semibold">
                T2101J
              </h1>
              <p className="text-sm max-w-xs">4 Semesters</p>
            </div>
            <div className='col-span-2 grid content-center'>
              <h1 className="text-medium">Oct 12</h1>
            </div>
            <div className='col-span-2 grid content-center'>
              <h1 className="text-medium">Oct 12</h1>
            </div>
            <div className='col-span-2 grid content-center'>
                <h1 className="text-medium font-semibold">
                    Trần Phương Nam
                </h1>
                <p className="text-sm max-w-xs">M12445A</p>
            </div>
            <div className='col-span-2 grid justify-center'>
              <h1 className="text-medium mr-3">15/30</h1>
            </div>
            <div className='col-span-2 grid content-center justify-end'>
              <span className="text-center inline-flex items-center bg-rose-100 text-rose-800 text-sm font-medium px-2.5 py-0.5 rounded-full">
                Fourth Semester
              </span>
            </div>
          </div>
        </div>
      </div>
      <div className='col-span-2 grid'>
        <div>
            <Calendar
                mode="single"
                selected={date}
                onSelect={setDate}
                className="rounded-md border mt-5"
            />
        </div>
        <div>
            <ScrollArea className="h-[355px] rounded-md border p-4 mt-8 bg-[#12121E] text-white">
                <div className='grid grid-cols-12 gap-4 relative'>
                    <h4 className="text-xl font-semibold col-span-1">Events</h4>
                    <div className='col-span-12 border rounded-xl p-2 grid grid-cols-12'>
                        <div className='col-span-4 bg-white rounded-md w-[50px] h-[50px] text-black text-center'>
                            <h4 className='text-lg font-semibold'>3</h4>
                            <p className='text-sm'>Feb</p>
                        </div>
                        <div className='col-span-8'>
                            <p className='text-sm font-medium'>Teacher vs. Student Competition</p>
                        </div>
                        <Separator className="my-2 col-span-12" />
                        <div className='col-span-12 grid grid-cols-4'>
                            <div className='col-span-1 flex'>
                                <MapPin className='w-5 h-5' />
                                <p className='text-xs ml-1 self-center'>1.2A</p>
                            </div>
                            <div className='col-span-3 flex justify-self-end'>
                                <AlarmClock className='w-5 h-5' />
                                <p className='text-xs ml-1 self-center'>08:00 - 17:00</p>
                            </div>
                        </div>
                    </div>
                    <div className='col-span-12 border rounded-xl p-2 grid grid-cols-12'>
                        <div className='col-span-4 bg-white rounded-md w-[50px] h-[50px] text-black text-center'>
                            <h4 className='text-lg font-semibold'>5</h4>
                            <p className='text-sm'>Aug</p>
                        </div>
                        <div className='col-span-8'>
                            <p className='text-sm font-medium'>Career Day</p>
                        </div>
                        <Separator className="my-2 col-span-12" />
                        <div className='col-span-12 grid grid-cols-4'>
                            <div className='col-span-1 flex'>
                                <MapPin className='w-5 h-5' />
                                <p className='text-xs ml-1 self-center'>1.2A</p>
                            </div>
                            <div className='col-span-3 flex justify-self-end'>
                                <AlarmClock className='w-5 h-5' />
                                <p className='text-xs ml-1 self-center'>08:00 - 17:00</p>
                            </div>
                        </div>
                    </div>
                    <div className='col-span-12 border rounded-xl p-2 grid grid-cols-12'>
                        <div className='col-span-4 bg-white rounded-md w-[50px] h-[50px] text-black text-center'>
                            <h4 className='text-lg font-semibold'>12</h4>
                            <p className='text-sm'>May</p>
                        </div>
                        <div className='col-span-8'>
                            <p className='text-sm font-medium'>Graduation Celebration</p>
                        </div>
                        <Separator className="my-2 col-span-12" />
                        <div className='col-span-12 grid grid-cols-4'>
                            <div className='col-span-1 flex'>
                                <MapPin className='w-5 h-5' />
                                <p className='text-xs ml-1 self-center'>1.2A</p>
                            </div>
                            <div className='col-span-3 flex justify-self-end'>
                                <AlarmClock className='w-5 h-5' />
                                <p className='text-xs ml-1 self-center'>08:00 - 17:00</p>
                            </div>
                        </div>
                    </div>
                    <div className='col-span-12 border rounded-xl p-2 grid grid-cols-12'>
                        <div className='col-span-4 bg-white rounded-md w-[50px] h-[50px] text-black text-center'>
                            <h4 className='text-lg font-semibold'>5</h4>
                            <p className='text-sm'>Jun</p>
                        </div>
                        <div className='col-span-8'>
                            <p className='text-sm font-medium'>Workshop: World Architecture Day - Create Your Own Unique Works</p>
                        </div>
                        <Separator className="my-2 col-span-12" />
                        <div className='col-span-12 grid grid-cols-4'>
                            <div className='col-span-1 flex'>
                                <MapPin className='w-5 h-5' />
                                <p className='text-xs ml-1 self-center'>1.2A</p>
                            </div>
                            <div className='col-span-3 flex justify-self-end'>
                                <AlarmClock className='w-5 h-5' />
                                <p className='text-xs ml-1 self-center'>08:00 - 17:00</p>
                            </div>
                        </div>
                    </div>
                    <div className='col-span-12 border rounded-xl p-2 grid grid-cols-12'>
                        <div className='col-span-4 bg-white rounded-md w-[50px] h-[50px] text-black text-center'>
                            <h4 className='text-lg font-semibold'>3</h4>
                            <p className='text-sm'>Feb</p>
                        </div>
                        <div className='col-span-8'>
                            <p className='text-sm font-medium'>Teacher vs. Student Competition</p>
                        </div>
                        <Separator className="my-2 col-span-12" />
                        <div className='col-span-12 grid grid-cols-4'>
                            <div className='col-span-1 flex'>
                                <MapPin className='w-5 h-5' />
                                <p className='text-xs ml-1 self-center'>1.2A</p>
                            </div>
                            <div className='col-span-3 flex justify-self-end'>
                                <AlarmClock className='w-5 h-5' />
                                <p className='text-xs ml-1 self-center'>08:00 - 17:00</p>
                            </div>
                        </div>
                    </div>
                    <div className='col-span-12 border rounded-xl p-2 grid grid-cols-12'>
                        <div className='col-span-4 bg-white rounded-md w-[50px] h-[50px] text-black text-center'>
                            <h4 className='text-lg font-semibold'>3</h4>
                            <p className='text-sm'>Feb</p>
                        </div>
                        <div className='col-span-8'>
                            <p className='text-sm font-medium'>Teacher vs. Student Competition</p>
                        </div>
                        <Separator className="my-2 col-span-12" />
                        <div className='col-span-12 grid grid-cols-4'>
                            <div className='col-span-1 flex'>
                                <MapPin className='w-5 h-5' />
                                <p className='text-xs ml-1 self-center'>1.2A</p>
                            </div>
                            <div className='col-span-3 flex justify-self-end'>
                                <AlarmClock className='w-5 h-5' />
                                <p className='text-xs ml-1 self-center'>08:00 - 17:00</p>
                            </div>
                        </div>
                    </div>
                    <div className='col-span-12 border rounded-xl p-2 grid grid-cols-12'>
                        <div className='col-span-4 bg-white rounded-md w-[50px] h-[50px] text-black text-center'>
                            <h4 className='text-lg font-semibold'>3</h4>
                            <p className='text-sm'>Feb</p>
                        </div>
                        <div className='col-span-8'>
                            <p className='text-sm font-medium'>Teacher vs. Student Competition</p>
                        </div>
                        <Separator className="my-2 col-span-12" />
                        <div className='col-span-12 grid grid-cols-4'>
                            <div className='col-span-1 flex'>
                                <MapPin className='w-5 h-5' />
                                <p className='text-xs ml-1 self-center'>1.2A</p>
                            </div>
                            <div className='col-span-3 flex justify-self-end'>
                                <AlarmClock className='w-5 h-5' />
                                <p className='text-xs ml-1 self-center'>08:00 - 17:00</p>
                            </div>
                        </div>
                    </div>
                </div>  
            </ScrollArea>
        </div>
      </div>
    </div>
  )
}
