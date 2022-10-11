import { Paper, styled, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material'
import React from 'react'
import { useNavigate } from 'react-router'
import { DataTableProps, DataRowType } from '../../../shared/types/interfaces'



const StyledTableHeaderCell = styled(TableCell)({
    backgroundColor: "orange",
    fontWeight: "900",
    color: "white",
})

const StyledTableRow = styled(TableRow)({
    ":hover": {
        "background": "lightgrey",
        "cursor": "pointer"
    }
})

const StyledTableCell = styled(TableCell)({
    fontSize: "1em"
})


const DataTable: React.FC<DataTableProps> = (props) => {
    const navigate = useNavigate()

    return (
        <TableContainer component={Paper} sx={{ maxHeight: "500px" }}>
            <Table stickyHeader>
                <TableHead>
                    <TableRow>
                        {props.headerKeyTemplate.map(headerKeyPair =>
                            <StyledTableHeaderCell key={headerKeyPair.headerName}>{headerKeyPair.headerName}</StyledTableHeaderCell>)}
                    </TableRow>
                </TableHead>
                <TableBody>
                    {props.data.map((dataRow: DataRowType) => {
                        return (
                            <StyledTableRow key={dataRow.id} onClick={() => navigate("/operations/" + dataRow.id)}>

                                {props.headerKeyTemplate.map(keyHeaderPair =>
                                    <StyledTableCell key={keyHeaderPair.key}>{dataRow[keyHeaderPair.key]}</StyledTableCell>)}
                            </StyledTableRow>
                        )
                    })
                    }
                </TableBody>
            </Table>
        </TableContainer>
    )
}

export default DataTable