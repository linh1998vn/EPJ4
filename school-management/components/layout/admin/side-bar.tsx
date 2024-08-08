import React from 'react'
import { AppWindow, BookOpen, Home, LogOut, MessageCircle, Settings, User } from 'lucide-react';
import Link from 'next/link';


export default function AdminSideBar() {
  return (
    <aside id="logo-sidebar" className="fixed top-0 left-0 z-40 w-64 h-screen transition-transform -translate-x-full sm:translate-x-0" aria-label="Sidebar">
   <div className="h-full px-3 py-4 overflow-y-auto bg-gray-50 dark:bg-gray-800">
      <a href="https://flowbite.com/" className="flex items-center pl-2.5 mb-5">
         <img src="https://flowbite.com/docs/images/logo.svg" className="h-6 mr-3 sm:h-7" alt="Flowbite Logo" />
         <span className="self-center text-xl font-semibold whitespace-nowrap dark:text-white">Flowbite</span>
      </a>
      <ul className="space-y-5 font-medium pt-24">
         <li>
            <Link href="#" className="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700">
                <Home />
               <span className="ml-3">Home</span>
            </Link>
         </li>
         <li>
            <Link href="#" className="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700">
                <AppWindow />
               <span className="flex-1 ml-3 whitespace-nowrap">Table</span>
            </Link>
         </li>
         <li>
            <Link href="#" className="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700">
                <BookOpen />
               <span className="flex-1 ml-3 whitespace-nowrap">Class</span>
            </Link>
         </li>
         <li>
            <Link href="#" className="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700">
                <User />
               <span className="flex-1 ml-3 whitespace-nowrap">Profile</span>
            </Link>
         </li>
         <li>
            <Link href="#" className="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700">
                <Settings />
               <span className="flex-1 ml-3 whitespace-nowrap">Products</span>
            </Link>
         </li>
      </ul>

      <ul className="space-y-5 font-medium pt-56">
         <li>
            <Link href="#" className="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700">
            <MessageCircle />
               <span className="flex-1 ml-3 whitespace-nowrap">Support</span>
            </Link>
         </li>
         <li>
            <Link href="#" className="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700">
            <LogOut />
               <span className="flex-1 ml-3 whitespace-nowrap">Log Out</span>
            </Link>
         </li>
      </ul>
   </div>
    </aside>
  )
}