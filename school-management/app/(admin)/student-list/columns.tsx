"use client"

import { DataTableRowActions } from "@/components/data-table/row-action"
import { Checkbox } from "@/components/ui/checkbox"
import { ColumnDef } from "@tanstack/react-table"

export type Student = {
  id: number
  firstName: string
  lastName: string
  phone: string
  address: string
  birthday: string
  enable: boolean
}

export const columns: ColumnDef<Student>[] = [
  {
    id: "select",
    header: ({ table }) => (
      <Checkbox
        checked={table.getIsAllPageRowsSelected()}
        onCheckedChange={(value) => table.toggleAllPageRowsSelected(!!value)}
        aria-label="Select all"
        className="translate-y-[2px]"
      />
    ),
    cell: ({ row }) => (
      <Checkbox
        checked={row.getIsSelected()}
        onCheckedChange={(value) => row.toggleSelected(!!value)}
        aria-label="Select row"
        className="translate-y-[2px]"
      />
    )
  },
  {
    accessorKey: "id",
    header: "Id",
    cell: ({ row }) => <div className="w-[10px]">{row.getValue("id")}</div>
  },
  {
    accessorKey: "firstName",
    header: "First Name",
    cell: ({ row }) => <div className="w-[50px]">{row.getValue("firstName")}</div>
  },

  {
    accessorKey: "lastName",
    header: "Last Name",
  },
  {
    accessorKey: "phone",
    header: "Phone Number",
  },
  {
    accessorKey: "address",
    header: "Address",
  },
  {
    accessorKey: "birthday",
    header: "Birthday",
  },
  {
    id: "actions",
    cell: ({ row }) => <DataTableRowActions row={row} />,
  },
]
