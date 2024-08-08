"use client"

import { DataTableRowActions } from "@/components/data-table/row-action"
import { Checkbox } from "@/components/ui/checkbox"
import { ColumnDef } from "@tanstack/react-table"

export type Teacher = {
  id: number
  code: string
  name: string
}

export const columns: ColumnDef<Teacher>[] = [
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
    accessorKey: "code",
    header: "Code",
    cell: ({ row }) => <div className="w-[50px]">{row.getValue("code")}</div>
  },
  {
    accessorKey: "name",
    header: "Name",
  },
  {
    id: "actions",
    cell: ({ row }) => <DataTableRowActions row={row} />,
  },
]
